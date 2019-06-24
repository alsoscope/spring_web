//handlebars 템플릿 적용. 템플릿에 필요한 정보를 JavaScript의 객체로 만드는 작업.

//파일이 이미지인 경우. checkImageType()을 이용해 썸네일 이미지 파일의 경로를 계산하거나, 다운로드, 원본 이미지 파일의 경로 계산
function checkImageType(fileName){
	//정규표현식을 이용해 파일의 확장자 존재여부 확인(i의 의미는 대, 소문자 고문 없음)
	var pattern=/jpg|gif|png|jpeg/i;
	
	return fileName.match(pattern);
}

//템플릿에 필요한 객체 생성
//템플릿을 적용해서 온전한 HTML을 구성한 후 첨부된 파일이 보여지는 $('.uploadedList')의 일부로 추가
function getFileInfo(fullName){//파라미터로 서버에서 전송된 파일의 이름 사용
	
	//fileName(경로, UUID '_' 제외된 파일 이름), imgsrc(썸네일 혹은 파일 이미지 경로), getLink(화면에서 원본 파일을 볼 수 있는 링크를 위한 변수)
	var fileName, imgsrc, getLink;
	
	var fileLink;
	
	var imgsrc=document.getElementId('imgsrc');
	
	//파일이 이미지인 경우 체크
	if(checkImageType(fullName)){
		imgsrc="/displayFile?fileName=";
		fileLink=fullName.substr(14);
		
		var front=fullName.substr(0,12); // /년/월/일의 경로를 추출하는 용도
		var end=fullName.substr(14); //썸네일을 나타내는 파일 이름 앞의 '_s'를 제거하는 용도
		
		window.open("/displayFile?fileName="+front+end, "새 창", "width=800, height=700");
	
	}else{		
			imgsrc.style.height="100px";
			imgsrc.style.width="200px";
	
		imgsrc="/resources/images/file.png";
		fileLink=fullName.substr(12);
		getLink="/displayFile?fileName="+fullName;
	}
	//일반 파일의 이름이 너무 길게 출력될 때 이를 줄여주는 기능
	//첨부파일의 이름이 uuid와 결합될 때 '_'가 사용되는 것을 이용해 원래의 파일 이름만을 추출
	fileName=fileLink.substr(fileLink.indexOf("_")+1);
	
	//리턴 값은 정보들을 JS의 객체로 생성해서 반환, product_regist.jsp에서 템플릿을 이용해 화면에 보여지도록 함
	return {fileName:fileName, imgsrc:imgsrc, getLink:getLink, fullName:fullName};
}