package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MyTools;

import dao.AdImagDao;
import domain.AdImagInfo;

/**
 * Servlet implementation class AdImagAddAction
 */

public class AdImagAddAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdImagAddAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		AdImagDao adImagDao = new AdImagDao(); //瀹氫箟瀵规暟鎹簱鎿嶄綔瀹炰綋
		AdImagInfo adImagInfo = new AdImagInfo();
		AdImagInfo adImag = new AdImagInfo();
		ArrayList<AdImagInfo> adImaglist = new ArrayList<AdImagInfo>(); 
		adImaglist = adImagDao.getAllAdImag();
		adImag = adImaglist.get(adImaglist.size()-1); //寰楀埌鏈�鍚庝竴寮犲浘鐗�
		adImagInfo.setAd_id(adImag.getAd_id()+1); //灏嗗箍鍛婂浘鐗囪〃鐨勫ぇ灏忓姞涓�鍗充负鏂板姞鐨勫箍鍛婂浘鐗囩殑id锛坕d浠�1閫掑锛�
		adImagInfo.setImg_path(MyTools.toChinese((String)request.getSession().getAttribute("path")));
		adImagDao.getInsertAdImag(adImagInfo); //娣诲姞鍥剧墖
		response.sendRedirect(path+"/servlet/AdImagListAction");
	}

}
