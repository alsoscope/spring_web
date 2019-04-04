package com.p.project.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.p.project.DTO.BoardVO;
import com.p.project.DTO.Criteria;
import com.p.project.DTO.PageMaker;
import com.p.project.Service.BoardService;

//Controller 흐름제어
//게시판 목록, 글쓰기, 상세보기(조회수 증가처리), 수정, 삭제
@Controller //현재 클래스를 컨트롤러 bean으로 등록
@RequestMapping("/board/*")
public class BoardController {
	
	private static final Logger logger=LoggerFactory.getLogger(BoardController.class);
	
	//의존관계 주입 ->BoardServiceImpl 생성
	//IoC 의존관계 역전
	//'@Autowired'및 '@Inject'주석이 동일하게 작동한다고 판단했습니다. 이 두 주석 모두 'AutowiredAnnotationBeanPostProcessor'를 사용하여 종속성을 주입합니다. 
	//'@Autowired'와 '@Inject'는 Spring bean을 삽입하기 위해 상호 교환 적으로 사용될 수 있습니다. 
	@Inject
	BoardService boardService;
	
	//01.게시글 목록
	@RequestMapping("list.do")
	// @RequestParam(defaultValue="") == 기본값 할당
	public ModelAndView list(@RequestParam(defaultValue="title") String searchOption, @RequestParam(defaultValue="") String keyword) throws Exception{
		List<BoardVO> list=boardService.listAll(searchOption, keyword);	
		//레코드의 개수
		int count=boardService.countArticle(searchOption, keyword);
		ModelAndView mav=new ModelAndView();
		//데이터를 맵에 저장
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("list", list);
		map.put("count", count); //레코드 개수
		map.put("searchOption", searchOption);//검색 옵션
		map.put("keyword", keyword);//검색 키워드
		mav.setViewName("board/list");//뷰를 list.jsp로 설정
		mav.addObject("map", map);//맵에 저장된 데이터를 mav에 저장
		//System.out.println("list.toString()");
		return mav;
	}
	
	//list with page
	@RequestMapping(value="listCri", method=RequestMethod.GET)
	public void listAll(Criteria cri, Model model) throws Exception{
		logger.info("show list page with Criteria ......");
		model.addAttribute("list", boardService.listCriteria(cri));
	}
	
	@RequestMapping(value="listPage", method=RequestMethod.GET)
	public void listPage(@ModelAttribute("cri")Criteria cri, Model model) throws Exception{
		logger.info(cri.toString());
		
		model.addAttribute("list", boardService.listCriteria(cri));
		PageMaker pageMaker=new PageMaker();
		pageMaker.setCri(cri);
		//pageMaker.setTotalCount(131); sample paging
		pageMaker.setTotalCount(boardService.listCountCriteria(cri));
		model.addAttribute("pageMaker", pageMaker);
	}
	
	//02_1 게시글 작성화면
	//@RequestMapping("board/write.do")
	//value="", method="전송방식"
	@RequestMapping(value="write.do", method=RequestMethod.GET)
	public String write() {
		return "board/write";
	}
	
	//02_02 게시글 작성 처리
	@RequestMapping(value="insert.do",method=RequestMethod.POST)
	public String insert(@ModelAttribute BoardVO vo, RedirectAttributes rttr, Model model)throws Exception{
		boardService.create(vo);

		//model.addAttribute("result", "success");
		rttr.addFlashAttribute("msg", "success");
		return "redirect:listPage.do";
	}
	
	//페이징 처리가 된 후, 조회 페이지는 다시 목록 페이지로 돌아간다. 현재 목록 페이지의 페이지 번호page, 페이지당 데이터수(perPageNum),조회하는 게시물의 번호 bno
	//03 게시글 상세내용 조회, 게시글 조회수 증가 처리
	//@RequestParam:get/post 방식으로 전달된 변수 1개
	//HttpSession 세션 객체
	@RequestMapping(value="viewPage",method=RequestMethod.GET)
	public void view(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri, Model model, HttpSession session)throws Exception{
		//조회수 증가 처리
		boardService.increaseViewcnt(bno, session);
		
		/*ModelAndView mav=new ModelAndView(); //모델(데이터)+뷰(화면)를 함께 전달하는 객체
		mav.setViewName("board/view"); //뷰의 이름
		mav.addObject("dto",boardService.read(bno)); //뷰에 전달할 데이터*/
		
		model.addAttribute("dto", boardService.read(bno));
		//return "board/viewPage";
	}
	
	//04 게시글 수정
	//폼에서 입력한 내용들은 @ModelAttribute BoardDTO dto로 전달됨
	@RequestMapping(value="updateGet")
	public String updateGet(int bno, Model model, @ModelAttribute("cri") Criteria cri) throws Exception {
		model.addAttribute("dto", boardService.read(bno));
		return "board/update";
	}
	
	@RequestMapping(value="updatePost",method=RequestMethod.POST)
	public String update(@ModelAttribute BoardVO vo, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr)throws Exception{
		boardService.update(vo);
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum",cri.getPerPageNum());
		rttr.addFlashAttribute("msg","success");
		return "redirect:listPage";
	}
	
	//05 게시글 삭제
	@RequestMapping("delete.do")
	public String delete(@RequestParam int bno, Criteria cri, RedirectAttributes rttr)throws Exception{
		boardService.delete(bno);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg", "success");
		return "redirect:listPage.do";
	}
}//BoardController
