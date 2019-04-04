package com.p.project.Controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.p.project.DTO.BoardVO;
import com.p.project.DTO.PageMaker;
import com.p.project.DTO.SearchCriteria;
import com.p.project.Service.BoardService;

@Controller
@RequestMapping("/sboard/*")
public class SearchBoardController {

	private static final Logger logger=LoggerFactory.getLogger(SearchBoardController.class);
	
@Inject
private BoardService service;

	//검색처리와 동적SQL
	@RequestMapping(value="searchList", method=RequestMethod.GET)
	public void listPage(@ModelAttribute("cri")SearchCriteria cri, Model model)throws Exception{
		logger.info(cri.toString());
		
		//model.addAttribute("list", service.listCriteria(cri));
		model.addAttribute("list", service.listSearchCriteria(cri));
		
		PageMaker pageMaker=new PageMaker();
		pageMaker.setCri(cri);
		
		//pageMaker.setTotalCount(service.listCountCriteria(cri));
		pageMaker.setTotalCount(service.listSearchCount(cri));
	
		model.addAttribute("pageMaker", pageMaker);
	}

	//조회
	@RequestMapping(value="viewPage", method=RequestMethod.GET)
	public void read(@RequestParam("bno")int bno, Model model, @ModelAttribute("cri")SearchCriteria cri) throws Exception{
		model.addAttribute("dto",service.read(bno));
	}
	
	//수정 GET방식
	@RequestMapping(value="updateGet", method=RequestMethod.GET)
	public String updateGet(int bno, @ModelAttribute("cri")SearchCriteria cri, Model model)throws Exception{
		model.addAttribute("dto", service.read(bno));
		return "sboard/update";
	}
	
	//수정 POST방식
	@RequestMapping(value="updatePost", method=RequestMethod.POST)
	public String updatePost(@ModelAttribute("vo")BoardVO vo, SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		logger.info(cri.toString());
		
		service.update(vo);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("ketword", cri.getKeyword());
		
		rttr.addFlashAttribute("msg", "success");
		
		logger.info(rttr.toString());
		return "redirect:/sboard/searchList";
	}
	
	//삭제
	@RequestMapping(value="delete.do", method=RequestMethod.POST)
	public String remove(@RequestParam("bno")int bno, SearchCriteria cri, RedirectAttributes rttr) throws Exception{
		service.delete(bno);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		rttr.addFlashAttribute("msg", "success");
		return "redirect:/sboard/searchList.do";
	}
	
	//글등록 form GET방식
	@RequestMapping(value="register", method=RequestMethod.GET)
	public void register() throws Exception {
		logger.info("register get........");
	}
	
	//글등록 POST처리
	@RequestMapping(value="registerPOST", method=RequestMethod.POST)
	public String registerPOST(BoardVO vo, RedirectAttributes rttr) throws Exception {
		logger.info("regist post...........");
		logger.info(vo.toString());
		
		service.create(vo);
		
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/sboard/searchList";
	}
	
}//SearchBoardController
