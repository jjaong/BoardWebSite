package com.jw.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy{
	@Override
	public File rename(File originFile) {
		String originName = originFile.getName(); //aaa.jpg
		
		//수정파일명 => 파일이 업로드된 시간 + 랜덤 5자리 + 확장자
		
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		int ranNum = (int)(Math.random()*90000) + 10000;
		
		String ext = originName.substring(originName.lastIndexOf("."));
		
		String changeName = "KaKaoTalk_" + currentTime + "_" + ranNum + ext;
		
		return new File(originFile.getParent(),changeName);
	}
}
