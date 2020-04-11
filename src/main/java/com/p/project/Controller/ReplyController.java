package com.p.project.Controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.p.project.DTO.Criteria;
import com.p.project.DTO.PageMaker;
import com.p.project.DTO.ReplyVO;
import com.p.project.Service.ReplyService;


//스프링 버전 3이므로 RestControlelr X, ResponseBody 어노테이션 사용
@Controller
@RequestMapping("/replies")
public class ReplyController {

	private static final Logger logger=LoggerFactory.getLogger(ReplyController.class);
	
	@Inject
	private ReplyService service;
	
	//1. JSON타입으로 데이터 전송. 댓글등록
	//ResponseBody annotation : return되는 값은 view가 아닌 HTTP ResponseBody에 직접 쓰여지게 된다
	@ResponseBody
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody ReplyVO vo, HttpSession session, @RequestParam Map<String, Object> hashMap){
		ResponseEntity<String> entity=null;
		
		try {
			String userId=(String)session.getAttribute("userId");
			vo.setReplyer(userId);

			service.addReply(vo);
			entity=new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			logger.info("Reply set userId : " + userId);
			logger.info("댓글 : " + vo);		
			
			//패스워드 암호화
			/*ShaPasswordEncoder encoder=new ShaPasswordEncoder(256);
			String password=encoder.encodePassword(hashMap.get("replyPw").toString(), null);
			hashMap.put("replyPw", password);*/
			
		}catch(Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}//register()--------------------------
	
	//2. 전체 댓글 목록 처리
	/*@ResponseBody
	@RequestMapping(value="/all/{bno}", method=RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno") int bno, ReplyVO vo){
		ResponseEntity<List<ReplyVO>> entity=null;	
		try {
			entity=new ResponseEntity<>(service.listReply(bno), HttpStatus.OK);			
			logger.info("ReplyList called by ReplyController");
		}catch(Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}//list()-------------------------------
*/	
	//3. 댓글 수정. REST방식에서 update. 작업은 PUT(전체 데이터 수정),PATCH(일부 데이터 수정)방식으로 처리.
	@ResponseBody
	@RequestMapping(value="/{rno}", method= {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> update(@PathVariable("rno") int rno, @RequestBody ReplyVO vo, Model model) throws Exception{
		ResponseEntity<String> entity=null;
		
		/*String replyer = (String)vo.get("replyer");
		String replyPw = (String)vo.get("replyPw");*/
		String replyer = (String)vo.getReplyer();
		String replyPw = (String)vo.getReplyPw();
		logger.info("replyer : " + replyer + ", replyPw : " + replyPw);
		
		try{
			/*Map<String, String>으로 받을 때
			boolean result=service.pwConfirm(vo.get(replyer), vo.get(replyPw));*/
			boolean result=service.pwConfirm(vo.getReplyer(), vo.getReplyPw());
			logger.info("result : " + result);//비밀번호 공백/불일치시 false, 맞으면 true 출력
			
			if(result) {
				/*JSONParser jsonParser = new JSONParser();
				JSONObject jsonObj=(JSONObject)jsonParser.parse(vo.getReplyer());
			
				JSONArray arrObj=(JSONArray)jsonObj.get(vo);
				
				System.out.println("====JSON====");
				for(int i=0; i<arrObj.size(); i++) {
					JSONObject tempObj = (JSONObject)arrObj.get(i);
					System.out.println(tempObj.get(vo.getReplyer()));
				}
				
				String replyer=(String)jsonObj.get("replyer");
*/
				vo.setRno(rno);
				service.modifyReply(vo);

				entity=new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
				logger.info("Reply Update SUCCESS : " + vo);
			} else {
				entity=new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
				model.addAttribute("message", "비밀번호가 맞지 않습니다.");	
			}			
		}catch(Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}//update()---------------------
	
	//4. 댓글 삭제. 삭제처리는 PUT과 유사하지만 추가적인 데이터가 없다
	@ResponseBody
	@RequestMapping(value="/{rno}", method=RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable("rno") int rno){
		ResponseEntity<String> entity=null;
		
		try {
			service.removeReply(rno);
			entity=new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			logger.info("Reply Delete SUCCESS : " + rno);
		}catch(Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST );
		}
		return entity;
	}//remove() -------------------
	
	//5. 댓글 목록, 페이징 처리. 두 개의 @PathVariable 이용해서 처리
	@ResponseBody
	@RequestMapping(value="/{bno}/{page}", method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> listPage(@PathVariable("bno")int bno, @PathVariable("page") int page, ReplyVO vo, HttpServletResponse response, HttpServletRequest request) throws Exception{
		ResponseEntity<Map<String, Object>> entity=null;
		
		try {
			Criteria cri=new Criteria();
			cri.setPage(page);
			
			PageMaker pageMaker=new PageMaker();
			pageMaker.setCri(cri);
			
			Map<String, Object> map=new HashMap<String, Object>();
			List<ReplyVO> list=service.listReplyPage(bno, cri);		
			map.put("list", list);
			
			int replyCount=service.count(bno);
			pageMaker.setTotalCount(replyCount);
			
			map.put("pageMaker", pageMaker);

			entity=new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
			
			//logger.info("listPage vo : " + vo);			
			logger.info("ReplyList called by listPage");
			
		}catch(Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<Map<String,Object>> (HttpStatus.BAD_REQUEST);
		}  
		return entity;
	}//listPage()-------------------
	
	/*@RequestMapping(value="/replyModal/{rno}", method=RequestMethod.POST)
	public void replyModal(Model model, ReplyVO vo, @PathVariable("rno") int rno) throws Exception{
		boolean confirm=service.pwConfirm(vo.getReplyer(), vo.getReplyPw());
		
		if(confirm) {
			vo.setRno(rno);
			service.modifyReply(vo);
			logger.info("Reply 수정 : " + vo);
		}else {
			model.addAttribute("message", "비밀번호가 맞지 않습니다.");	
		}
	}*/
	
	
}//ReplyController
