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


//������ ���� 3�̹Ƿ� RestControlelr X, ResponseBody ������̼� ���
@Controller
@RequestMapping("/replies")
public class ReplyController {

	private static final Logger logger=LoggerFactory.getLogger(ReplyController.class);
	
	@Inject
	private ReplyService service;
	
	//1. JSONŸ������ ������ ����. ��۵��
	//ResponseBody annotation : return�Ǵ� ���� view�� �ƴ� HTTP ResponseBody�� ���� �������� �ȴ�
	@ResponseBody
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody ReplyVO vo, HttpSession session, @RequestParam Map<String, Object> hashMap){
		ResponseEntity<String> entity=null;
		
		try {
			String userId=(String)session.getAttribute("userId");
			vo.setReplyer(userId);
			logger.info("Reply set userId : " + userId);

			service.addReply(vo);
			entity=new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			logger.info("��� �ۼ� ȸ�� : " + userId);		
			
			//�н����� ��ȣȭ
			/*ShaPasswordEncoder encoder=new ShaPasswordEncoder(256);
			String password=encoder.encodePassword(hashMap.get("replyPw").toString(), null);
			hashMap.put("replyPw", password);*/
			
		}catch(Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}//register()--------------------------
	
	//2. ��ü ��� ��� ó��
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
	//3. ��� ����. REST��Ŀ��� update. �۾��� PUT(��ü ������ ����),PATCH(�Ϻ� ������ ����)������� ó��.
	@ResponseBody
	@RequestMapping(value="/{rno}", method= {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> update(@PathVariable("rno") int rno, @RequestBody ReplyVO vo, Model model){
		ResponseEntity<String> entity=null;
		
		try{
			vo.setRno(rno);
			service.modifyReply(vo);

			entity=new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			logger.info("Reply Update SUCCESS : " + vo);
			
			/*boolean result=service.pwConfirm(vo.getReplyer(), vo.getReplyPw());
			if(result) {
				vo.setRno(rno);
				service.modifyReply(vo);

				entity=new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
				logger.info("Reply Update SUCCESS : " + vo);
			}else {
				model.addAttribute("message", "��й�ȣ�� ���� �ʽ��ϴ�.");	
			}			
*/		}catch(Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}//update()---------------------
	
	//4. ��� ����. ����ó���� PUT�� ���������� �߰����� �����Ͱ� ����
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
	
	//5. ��� ���, ����¡ ó��. �� ���� @PathVariable �̿��ؼ� ó��
	@ResponseBody
	@RequestMapping(value="/{bno}/{page}", method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> listPage(@PathVariable("bno")int bno, @PathVariable("page") int page, ReplyVO vo, HttpServletResponse response, HttpServletRequest request) throws Exception{
		ResponseEntity<Map<String, Object>> entity=null;
		
		try {
			String replyer=vo.getReplyer();
			logger.info("replyer : " + replyer);
			
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
		
			
			
			/*String result = service.listReplyPage(bno, cri); 
					
			//������ JSON ����
			JSONObject jsonObj=new JSONObject();*/
			
			
			
			
			
			/*JSONParser parser = new JSONParser();
			
			
			String replyer=(String)jsonObj.get("replyer");
			System.out.println("replyer : " + replyer);
			
			JSONArray arrObj=(JSONArray)jsonObj.get("list");
			JSONObject jsonObj2=(JSONObject)arrObj.get("bno");*/
	
			
			entity=new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
			
			logger.info("listPage vo : " + vo);			
			logger.info("ReplyList called by listPage");
			
		}catch(Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<Map<String,Object>> (HttpStatus.BAD_REQUEST);
		}  
		return entity;
	}//listPage()-------------------
	
}//ReplyController
