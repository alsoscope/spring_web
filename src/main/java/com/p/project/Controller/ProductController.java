package com.p.project.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.p.project.DTO.ProductDTO;
import com.p.project.DTO.SearchCriteria;
import com.p.project.FileUpload.FileUpload;
import com.p.project.FileUpload.MediaUtils;
import com.p.project.Service.BoardService;
import com.p.project.Service.MemberService;
import com.p.project.Service.ProductService;
import com.p.project.DTO.Criteria;
import com.p.project.DTO.MemberDTO;
import com.p.project.DTO.PageMaker;

//상품 관련 페이지 매핑
@Controller
@RequestMapping("/shop/product/*")
public class ProductController {
	
	private static final Logger logger=LoggerFactory.getLogger(ProductController.class);
	
	//xml에 설정된 리소스 참조
	//bean의 id가 uploadPath인 태그를 참조
	@Resource(name="uploadPath")
	private String uploadPath;
	
	@Inject
	ProductService productService;

	//1. 상품 등록 페이지 매핑
	@RequestMapping(value="product_regist")
	public String product_regist() {
		return "/product/product_regist";
	}
	
	//상품 등록 처리 매핑
	@RequestMapping(value="insertProduct", method=RequestMethod.POST)
	public String insertProduct(ProductDTO dto, ProductDTO product_id) throws Exception{
/*		String filename="";
		//첨부파일(상품사진)이 있으면
		if(!dto.getProduct_Photo().isEmpty()) {
			filename=dto.getProduct_Photo().getOriginalFilename();
			//개발 디렉토리 - 파일 업로드 경로
			//배포 디렉토리 - 파일 업로드 경로
			String path="c:\\";
			try {
				new File(path).mkdirs(); //이미지 파일을 저장할 디렉토리 생성
				//임시 디렉토리(서버)에 지정된 파일을 지정된 디렉토리로 전송.
				//path 디렉토리, filename 원본 이미지 파일명, 서버에 임시 저장된 파일을 저장 디렉토리로 전송
				dto.getProduct_Photo().transferTo(new File(path+filename));
			}catch(Exception e) {
				e.printStackTrace();
			}
			dto.setProduct_url(filename);
		}*/
		
		/*if(product_id==null) {
			return null;
		}else {
			productService.getAttach(product_id);
		}*/
	
		logger.info("insert Product : " + dto.toString());
		productService.insertProduct(dto);
		
		/*String[] files=(String[])dto.getFiles();
		logger.info("getFiles() : " + files);*/
		
		return "redirect:/shop/product/product_list_korean";
	}//insertProduct
	
	@RequestMapping(value="insertAbroad", method=RequestMethod.POST)
	public String insertAbroad(ProductDTO dto, ProductDTO product_id, HttpServletRequest request, HttpServletResponse response) throws Exception{	
		logger.info("insert insertAbroad : " + dto.toString());
		productService.insertAbroad(dto);
		
		//radio button의 value 값을 가져온다.
		//String abroad=request.getParameter("abroad");
		//dto.setGroup("abroad");
		
		/*PrintWriter out=response.getWriter();
		out.println("분류 : " + abroad);*/
		//System.out.println("분류 : " + abroad);
		
		return "redirect:/shop/product/product_list_abroad";
	}//insertAbroad
	
	@RequestMapping(value="insertEtcetera", method=RequestMethod.POST)
	public String insertEtcetera(ProductDTO dto, ProductDTO product_id) throws Exception{	
		logger.info("insert insertEtcetera : " + dto.toString());
		productService.insertEtcetera(dto);		
		return "redirect:/shop/product/product_list_etcetera";
	}//insertEtcetera
	
	//1. 상품 전체 목록 페이지 매핑. service에서 가져온 리스트 객체 리턴
/*	@RequestMapping(value="/", method=RequestMethod.GET)
	public String listProduct(Model model) {
		List<ProductDTO> list=productService.listProduct();
		model.addAttribute("list", list);
		System.out.println("list.toString()");
		return "product/product_list";
	}*/
	
	//2. 상품 상세보기 페이지 매핑. 하나의 URL이 하나의 고유한 리소스를 대표(Rest)할 수 있도록 처리
	@RequestMapping("/detail/{product_id}")
	public String detailProduct(Model model, @PathVariable("product_id")int product_id) throws Exception{
		
		model.addAttribute("vo", productService.detailProduct(product_id));
		logger.info("클릭한 product_id : " + product_id);
		
		return "/product/product_detail";
	}
	
	@RequestMapping("/detail_ab/{product_id}")
	public String detailAbroad(Model model, @PathVariable("product_id")int product_id,HttpServletRequest request) throws Exception{
		
		model.addAttribute("vo", productService.detailAbroad(product_id));
		logger.info("클릭한 product_id : " + product_id);
		
		//radio button의 value 값을 가져온다.
		String abroad=request.getParameter("abroad");
		if(abroad == null) {
			abroad = "AAbroad";
		}
		System.out.println("분류 : " + abroad);
		
		return "/product/product_detail_ab";
	}
	
	@RequestMapping("/detail_etc/{product_id}")
	public String detailEtcetera(Model model, @PathVariable("product_id")int product_id) throws Exception{
		
		model.addAttribute("vo", productService.detailEtcetera(product_id));
		logger.info("클릭한 product_id : " + product_id);
		
		return "/product/product_detail_etc";
	}
	
	//게시글 수정 form
	@RequestMapping(value="/product_update/{product_id}", method=RequestMethod.GET)
	public String product_update(ProductDTO dto, Model model, @PathVariable("product_id")int product_id, RedirectAttributes rttr) throws Exception{
		
/*		if(request.getParameter("abroad") == "AAbroad") {
			model.addAttribute("dto", productService.detailAbroad(product_id));
		}else {
		}
*/					
		model.addAttribute("dto", productService.detailProduct(product_id));
		
		rttr.addFlashAttribute("msg" , "success");
		logger.info("수정할 product_id : " + product_id);
		logger.info("----------product_update_form----------");
		return "/product/product_update";
	}

	@RequestMapping(value="/product_update_ab/{product_id}", method=RequestMethod.GET)
	public String product_update_ab(ProductDTO dto, Model model, @PathVariable("product_id")int product_id, RedirectAttributes rttr) throws Exception{
		model.addAttribute("dto", productService.detailAbroad(product_id));
		
		rttr.addFlashAttribute("msg" , "success");
		logger.info("수정할 product_id : " + product_id);
		logger.info("----------Abroad update form----------");
		return "/product/product_update_ab";
	}
	
	@RequestMapping(value="/product_update_etc/{product_id}", method=RequestMethod.GET)
	public String product_update_etc(ProductDTO dto, Model model, @PathVariable("product_id")int product_id, RedirectAttributes rttr) throws Exception{
		model.addAttribute("dto", productService.detailEtcetera(product_id));
		
		rttr.addFlashAttribute("msg" , "success");
		logger.info("수정할 product_id : " + product_id);
		logger.info("----------Etcetera update form----------");
		return "/product/product_update_etc";
	}
	
	//게시글 수정 post
	@RequestMapping(value="/updatePost", method=RequestMethod.POST)
	public String updatePost(@ModelAttribute ProductDTO dto) throws Exception{
		productService.updateProduct(dto);
		logger.info("------- updatePost -------");
		return "redirect:/shop/product/detail/" + dto.getProduct_id();
	}
	@RequestMapping(value="/updatePost_ab", method=RequestMethod.POST)
	public String updatePost_ab(@ModelAttribute ProductDTO dto) throws Exception{
		productService.updateAbroad(dto);
		logger.info("------- updateAbroad -------");
		return "redirect:/shop/product/detail_ab/" + dto.getProduct_id();
	}
	@RequestMapping(value="/updatePost_etc", method=RequestMethod.POST)
	public String updatePost_etc(@ModelAttribute ProductDTO dto) throws Exception{
		productService.updateEtcetera(dto);
		logger.info("------- updateEtcetera -------");
		return "redirect:/shop/product/detail_etc/" + dto.getProduct_id();
	}
	
	@RequestMapping("product_remove")
	public String product_remove(int product_id, RedirectAttributes rttr) throws Exception {
		
		/*if(request.getParameter("abroad") == "AAbroad") {
			productService.deleteAbroad(product_id);
		}*/
		productService.deleteProduct(product_id);		
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/";
	}
	@RequestMapping("product_remove_ab")
	public String product_remove_ab(int product_id, RedirectAttributes rttr) throws Exception {
		productService.deleteAbroad(product_id);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/";
	}
	@RequestMapping("product_remove_etc")
	public String product_remove_etc(int product_id, RedirectAttributes rttr) throws Exception {
		productService.deleteEtcetera(product_id);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/";
	}
	
	//첨부파일 이미지 (fullName) 불러오기
	@ResponseBody
	@RequestMapping("/getAttach/{product_id}")
	public List<String> getAttach(@PathVariable("product_id")int product_id) throws Exception{
		logger.info("getAttach : " + productService.getAttach(product_id).toString());
		return productService.getAttach(product_id);
	}
	
	@ResponseBody
	@RequestMapping("/getAttach_ab/{product_id}")
	public List<String> getAttach_ab(@PathVariable("product_id")int product_id) throws Exception{
		logger.info("getAttach : " + productService.getAttach_ab(product_id).toString());
		return productService.getAttach_ab(product_id);
	}
	
	@ResponseBody
	@RequestMapping("/getAttach_etc/{product_id}")
	public List<String> getAttach_etc(@PathVariable("product_id")int product_id) throws Exception{
		logger.info("getAttach : " + productService.getAttach_etc(product_id).toString());
		return productService.getAttach_etc(product_id);
	}
	
	//한국 영화 카테고리
	@RequestMapping(value="product_list_korean")
	public String product_list_korean(Model model, Criteria cri) throws Exception{
	
		System.out.println("product_list_korean");

		List<ProductDTO> list=productService.listProduct();
		//List<ProductDTO> list=productService.criteriaList(cri);
		model.addAttribute("vo", list);
		
		/*PageMaker pageMaker=new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(productService.listCountCriteria(cri));
		model.addAttribute("pageMaker", pageMaker);*/
		
		return "/product/product_list_korean";
	}
	
	//해외 영화 카테고리
	@RequestMapping("product_list_abroad")
	public String product_list_aborad(Model model, Criteria cri) {
		logger.info("product_list_abroad");
		List<ProductDTO> list=productService.listAbroad();
		model.addAttribute("vo", list);
		return "/product/product_list_abroad";
	}
	
	//기타 영상 카테고리
	@RequestMapping("product_list_etcetera")
	public String product_list_etcetera(Model model, Criteria cri) {
		logger.info("product_list_etcetera");
		List<ProductDTO> list=productService.listEtcetera();
		model.addAttribute("vo", list);
		return "/product/product_list_etcetera";
	}

	//Ajax로 파일 업로드 ----------------------------------------------------------------------------------------
	//파일 업로드 흐름 : 업로드 버튼 클릭 → 임시 디렉토리에 업로드 → 지정된 디렉토리에 저장 → 파일정보가 file에 저장
	//Ajax로 호출되는 특정 게시물의 첨부파일을 처리하는 메소드
	@ResponseBody //view가 아닌 data리턴. produces= : 파일의 한글처리
	@RequestMapping(value="/uploadAjax", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception{
		
		logger.info("uploadFile originalName : " + file.getOriginalFilename());
		/*logger.info("size : " + file.getSize());
		logger.info("contentType : " + file.getContentType());*/
		
		return new ResponseEntity<>(FileUpload.uploadFile(uploadPath,
				file.getOriginalFilename(), 
				file.getBytes()),
				HttpStatus.CREATED);//Http상태코드 CREATED 대신 OK라 해도 됨
	}
	//HttpStatus.CREATED : RESTFul 응답결과 상태.The resource was created successfully.
	
	//첨부파일 업로드 (글 수정 폼)
	/*@ResponseBody
	@RequestMapping(value="/uploadUpdate/{product_id}", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadUpdate(MultipartFile file) throws Exception{
		
		logger.info("uploadFile originalName : " + file.getOriginalFilename());
		
		return new ResponseEntity<>(FileUpload.uploadFile(uploadPath,file.getOriginalFilename(), file.getBytes()), HttpStatus.CREATED);//Http상태코드 CREATED 대신 OK라 해도 됨
	}*///uploadUpdate
	
	//displayFile()은 파라미터로 브라우저에서 전송받기를 원하는 파일의 이름을 받는다
	@ResponseBody
	@RequestMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName)throws Exception{
		
		//서버의 파일을 다운로드하기 위한 스트림
		InputStream in=null; //java.io
		
		//ResponseBody를 이용해서 byte[] 데이터가 그대로 전송될 것임을 명시.
		ResponseEntity<byte[]> entity=null;
		
		logger.info("displayFile FILE NAME : " + fileName);

		try {			

			//가장 먼저 하는 작업. 파일 이름에서 확장자 추출,formatName에 저장. 이미지 타입의 파일인 경우는 적절한 MIME 타입을 지정.
			String formatName=fileName.substring(fileName.lastIndexOf(".")+1);
			
			//추출한 확장자를 MediaUtils클래스에서 이미지 파일 여부를 검사하고 리턴받아 mType에 저장
			MediaType mType=MediaUtils.getMediaType(formatName);
			
			//헤더 구성 객체(외부에서 데이터를 주고받을 때에는 header와 body를 구성해야하기 때문에)
			HttpHeaders headers=new HttpHeaders();
			
			//InputStream 생성
			in = new FileInputStream(uploadPath+fileName);
			
				//이미지 파일인지 확인
				if(mType != null) {
					headers.setContentType(mType);
				}else {
					fileName=fileName.substring(fileName.indexOf("_")+1);
					
					//이미지가 아닌 경우 MIME 타입을 다운로드 용으로 사용되게 한다. 사용자에게 자동으로 다운로드 창을 열어줌.
					headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
					//한글처리. new String : 바이트 배열을 스트링으로. 
					headers.add("Content-Disposition", "attachment; filename=\""+new String(fileName.getBytes("UTF-8"),"ISO-8859-1")+"\"");
				}

				//실제로 데이터를 읽는 부분. commons 라이브러리의 기능을 활용해 대상 파일에서 데이터를 읽어내는 IOUtils.toByteArray()
				//바이트 패열, 헤더, HTTP 상태코드
				entity=new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers,HttpStatus.CREATED);		
				
			}catch(Exception e) {
				e.printStackTrace();
				entity=new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);//HTTP상태 코드()
			}finally {
				in.close();//스트림 닫기
			}
			return entity;
	}//displayFile
	
	//displayFile()은 파라미터로 브라우저에서 전송받기를 원하는 파일의 이름을 받는다
	/*@ResponseBody
	@RequestMapping("/displayFile_detail")
	public ResponseEntity<byte[]> displayFile_detail(String fileName)throws Exception{
		
		//서버의 파일을 다운로드하기 위한 스트림
		InputStream in=null; //java.io
		
		//ResponseBody를 이용해서 byte[] 데이터가 그대로 전송될 것임을 명시.
		ResponseEntity<byte[]> entity=null;
		
		logger.info("displayFile_detail : " + fileName);

		try {
			//가장 먼저 하는 작업. 파일 이름에서 확장자 추출,formatName에 저장. 이미지 타입의 파일인 경우는 적절한 MIME 타입을 지정.
			String formatName=fileName.substring(fileName.lastIndexOf(".")+1);
			
			//추출한 확장자를 MediaUtils클래스에서 이미지 파일 여부를 검사하고 리턴받아 mType에 저장
			MediaType mType=MediaUtils.getMediaType(formatName);
			
			//헤더 구성 객체(외부에서 데이터를 주고받을 때에는 header와 body를 구성해야하기 때문에)
			HttpHeaders headers=new HttpHeaders();
			
			//InputStream 생성
			in = new FileInputStream(uploadPath+fileName);
			
				//이미지 파일인지 확인
				if(mType != null) {
					headers.setContentType(mType);
				}else {
					fileName=fileName.substring(fileName.indexOf("_")+1);
					
					//이미지가 아닌 경우 MIME 타입을 다운로드 용으로 사용되게 한다. 사용자에게 자동으로 다운로드 창을 열어줌.
					headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
					//한글처리. new String : 바이트 배열을 스트링으로. 
					headers.add("Content-Disposition", "attachment; filename=\""+new String(fileName.getBytes("UTF-8"),"ISO-8859-1")+"\"");
				}

				//실제로 데이터를 읽는 부분. commons 라이브러리의 기능을 활용해 대상 파일에서 데이터를 읽어내는 IOUtils.toByteArray()
				//바이트 패열, 헤더, HTTP 상태코드
				entity=new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers,HttpStatus.CREATED);		
				
			}catch(Exception e) {
				e.printStackTrace();
				entity=new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);//HTTP상태 코드()
			}finally {
				in.close();//스트림 닫기
			}
			return entity;
	}//displayFile_detail
*/	
	//상품등록 폼에서 파일 삭제 매핑
	//파라미터로 삭제할 파일의 이름을 받는다. 이미지의 경우 썸네일 이름, 일반 파일은 실제 이름이 된다
	@ResponseBody
	@RequestMapping(value="/deleteFile", method=RequestMethod.POST)
		public ResponseEntity<String> deleteFile(String fileName){
			
			logger.info("deleteFile : " + fileName);
			
			//파일의 확장자 추출
			String formatName=fileName.substring(fileName.lastIndexOf(".")+1);
			
			//이미지 파일 여부 검사
			MediaType mType=MediaUtils.getMediaType(formatName);
			
			//이미지 파일이 확인되면 원본 파일+썸네일 파일 삭제, 이미지 아니면 원본파일만 삭제
			if(mType!=null) {
				//썸네일 이미지 파일 추출
				String front=fileName.substring(0, 12);
				String end=fileName.substring(14);
				
				//썸네일 이미지 삭제
				new File(uploadPath + (front+end).replace('/', File.separatorChar)).delete();
			}
			//원본 파일 삭제
			new File(uploadPath+fileName.replace('/', File.separatorChar)).delete();
			
			//데이터와 http상태 코드 전송
			return new ResponseEntity<String>("deleted", HttpStatus.OK);
		}
	
	//게시글 삭제시 기존의 첨부파일 함께 삭제
	@ResponseBody
	@RequestMapping(value="deleteAllFiles", method=RequestMethod.POST)
	public ResponseEntity<String> deleteAllFiles(@RequestParam("files[]")String[] files){
		logger.info("delete All Files : " + files);
		
		//여러 개의 파일 이름을 받을 수 있도록 String[]로 작성한다
		if(files == null || files.length==0) {
			return new ResponseEntity<String>("deleted", HttpStatus.OK);
		}
		
		for(String fileName : files) {
			String formatName=fileName.substring(fileName.lastIndexOf(".")+1);
			
			MediaType mType=MediaUtils.getMediaType(formatName);
			
			if(mType != null) {
				String front=fileName.substring(0,12);
				String end=fileName.substring(14);
				new File(uploadPath + (front+end).replace('/', File.separatorChar)).delete();
			}
			
			new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
		}
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
	
	/*무한 스크롤. 브라우저에서 요청으로 온 JSON데이터를 객체로 자동으로 바인딩 시켜주는 @RequestBody를 이용하여 product_id를 멤버로 갖고 있는
	ProductDTO 객체를 통해 product_id 값을 바인딩 시키고, 자동으로 리턴값을 JSON형태로 만들어주는 @ResponseBody 이용*/
	/*@RequestMapping(value="infiniteScrollDown", method=RequestMethod.POST)
	public @ResponseBody List<ProductDTO> infiniteScrollDown(@RequestBody ProductDTO productDTO){
		
		logger.info("infiniteScrollDown called...............");
		logger.info(productDTO.toString());
		
		Integer bnoStart = productDTO.getProduct_id()-1;
		
		return productService.infiniteScrollDown(bnoStart);
	}
	
	@RequestMapping(value="infiniteScrollUp", method=RequestMethod.POST)
	public @ResponseBody List<ProductDTO> infiniteScrollUp(@RequestBody ProductDTO productDTO){
		
		logger.info("infiniteScrollUp called...............");
		logger.info(productDTO.toString());
		
		Integer bnoStart = productDTO.getProduct_id()+1;
		
		return productService.infiniteScrollUp(bnoStart);
	}*/
}//ProductController
