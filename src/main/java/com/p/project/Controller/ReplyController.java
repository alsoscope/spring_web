package com.p.project.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.p.project.DTO.Criteria;
import com.p.project.DTO.PageMaker;
import com.p.project.DTO.ReplyVO;
import com.p.project.Service.ReplyService;

//������ ���� 3�̹Ƿ� RestControlelr X, ResponseBody ������̼� ���
@Controller
@RequestMapping("/replies")
public class ReplyController {

	@Inject
	private ReplyService service;
	
	//1. JSONŸ������ ������ ����. ��۵��
	//ResponseBody annotation : return�Ǵ� ���� view�� �ƴ� HTTP ResponseBody�� ���� �������� �ȴ�
	@ResponseBody
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody ReplyVO vo){
		ResponseEntity<String> entity=null;
		
		try {
			service.addReply(vo);
			entity=new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}//register()--------------------------
	
	//2. ��ü ��� ��� ó��
	@ResponseBody
	@RequestMapping(value="/all/{bno}", method=RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno") int bno){
		ResponseEntity<List<ReplyVO>> entity=null;
		
		try {
			entity=new ResponseEntity<>(service.listReply(bno), HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}//list()-------------------------------
	
	//3. ����ó�� REST��Ŀ��� update �۾��� PUT,PATCH������� ó��. ��ü ������ ������ PUT, �Ϻ� ������ ������ PATCH 
	@ResponseBody
	@RequestMapping(value="/{rno}", method= {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> update(@PathVariable("rno") int rno, @RequestBody ReplyVO vo){
		ResponseEntity<String> entity=null;
		
		try{
			vo.setRno(rno);
			service.modifyReply(vo);
			
			entity=new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}//update()---------------------
	
	//4. ����. ����ó���� PUT�� ���������� �߰����� �����Ͱ� ����
	@ResponseBody
	@RequestMapping(value="/{rno}", method=RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable("rno") int rno){
		ResponseEntity<String> entity=null;
		
		try {
			service.removeReply(rno);
			entity=new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST );
		}
		return entity;
	}//remove() -------------------
	
	//5.����¡ ó��. �� ���� @PathVariable �̿��ؼ� ó��
	@ResponseBody
	@RequestMapping(value="/{bno}/{page}", method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> listPage(@PathVariable("bno")int bno, @PathVariable("page") int page){
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
		}catch(Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<Map<String,Object>> (HttpStatus.BAD_REQUEST);
		}  
		return entity;
	}//listPage()-------------------

}//ReplyController