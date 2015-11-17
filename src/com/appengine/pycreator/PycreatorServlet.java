package com.appengine.pycreator;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

@SuppressWarnings("serial")
public class PycreatorServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		resp.setContentType("text/txt;  charset=utf-8");
		PrintWriter out = resp.getWriter();
				
		//out.println("<html><head></head><body><p>");
				
		String value = req.getParameter("textInput");
		System.out.println(value);
		//value = new String(value.getBytes("ISO-8859-1"),"UTF-8"); 
		String[] poList = value.split("\r\n|\r|\n");
		
		for (int x=0; x<poList.length; x++){
		
			if (poList[x] != null){
				char [] t1 = poList[x].toCharArray();
				String[] t2 = new String[10];

				net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
				t3.setCaseType(HanyuPinyinCaseType.UPPERCASE);
				t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
				t3.setVCharType(HanyuPinyinVCharType.WITH_V);

				try {
					for (int i=0;i<t1.length;i++)
					{
						t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
						if (t2 != null)
						{
							if (i==0) 
							{
								out.print(t2[0]);
								System.out.print(t2[0]);
							}
							else
							{
								out.print(" ");
								out.print(t2[0]);
								System.out.print(t2[0]);
							}

						}
						else
						{
							out.print(" ");
							out.print(t1[i]);
							System.out.print(t1[i]);						
						}
						//out.append(ConvertedWord);
					}

				}			
				catch (BadHanyuPinyinOutputFormatCombination e1) 
				{
					e1.printStackTrace();
				}

				//out.append("</br>");
				System.out.append("\n");
			}
			//out.println(poList[x] + "</br>");
		
		}
		//out.println("</p></body></html>");
	
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}
	
}
