package com.p.project.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.p.project.DTO.BoardVO;
import com.p.project.DTO.MemberDTO;
import com.p.project.DTO.PageMaker;
import com.p.project.DTO.SearchCriteria;
import com.p.project.Service.BoardService;
import com.p.project.VO.MemberVO;

@Controller
@RequestMapping("/sboard/*")
public class SearchBoardController {

	private static final Logger logger=LoggerFactory.getLogger(SearchBoardController.class);
	
@Inject
private BoardService service;

	//�˻�ó���� ����SQL
	@RequestMapping(value="search_list")
	public void listPage(@ModelAttribute("cri")SearchCriteria cri, Model model,String searchOption, String keyword)throws Exception{
		logger.info("SearchCriteria" + cri.toString());
		
		//model.addAttribute("list", service.listCriteria(cri));
		model.addAttribute("list", service.listSearch(cri));
		
		PageMaker pageMaker=new PageMaker();
		pageMaker.setCri(cri);
		
		//pageMaker.setTotalCount(service.listCountCriteria(cri));
		pageMaker.setTotalCount(service.listSearchCount(cri));
		model.addAttribute("pageMaker", pageMaker);
	
		int count=service.countArticle(searchOption, keyword);
		//model.addAttribute("count", count);
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("count", count);
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		model.addAttribute("map", map);
	}

	//�Խñ� ����
	@RequestMapping(value="viewPage", method=RequestMethod.GET)
	public void read(@RequestParam("bno")int bno, Model model, @ModelAttribute("cri")SearchCriteria cri,HttpSession session) throws Exception{
		
		//��ȸ�� ����
		service.increaseViewcnt(bno, session);
		
		model.addAttribute("dto",service.read(bno));
	}
	
	//�Խñ� ���� GET���
	@RequestMapping(value="/updateGet/", method=RequestMethod.GET)
	public String updateGet(int bno, @ModelAttribute("cri")SearchCriteria cri, Model model)throws Exception{
		model.addAttribute("dto", service.read(bno));
		logger.info("�����ϴ� �� ��ȣ : " + bno);
		
		return "/sboard/update";
	}
	
	//�Խñ� ���� POST���
	@RequestMapping(value="updatePost", method=RequestMethod.POST)
	public String updatePost(@ModelAttribute("vo")BoardVO vo, SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		service.update(vo);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("ketword", cri.getKeyword());
		rttr.addFlashAttribute("msg", "success");
		
		logger.info("�� ����  : " + vo);
		/*return "/sboard/viewPage/"+vo.getBno();*/
		return "redirect:/sboard/search_list";
	}
	
	//�Խñ� ����
	@RequestMapping(value="delete")
	public String remove(@RequestParam("bno")int bno, SearchCriteria cri, RedirectAttributes rttr) throws Exception{
		service.delete(bno);
		logger.info("�� ���� ��ȣ : " + bno);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		rttr.addFlashAttribute("msg", "success");
		return "redirect:/sboard/search_list";
	}
	
	//�Խñ� ��� form GET���
	@RequestMapping(value="register", method=RequestMethod.GET)
	public void register(@ModelAttribute("dto")MemberDTO memberdto, HttpSession session) throws Exception {
		logger.info("----------register get----------");
	}
	
	//�Խñ� ��� POSTó��
	@RequestMapping(value="registerPOST", method=RequestMethod.POST)
	public String registerPOST(BoardVO vo, RedirectAttributes rttr, HttpSession session) throws Exception {
		logger.info("regist post...........");
		logger.info(vo.toString());
		
		//�α����� ����ڰ� �� �ۼ��� ��� userId ����
		String userId=(String)session.getAttribute("userId");
		vo.setWriter(userId);
		logger.info("�ۼ��� userId : " + userId);
		
		service.create(vo);

		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/sboard/search_list";
	}
	
}//SearchBoardController
