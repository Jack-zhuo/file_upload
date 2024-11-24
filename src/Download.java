import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/download")
public class Download extends HttpServlet {
    String folder = "E:\\zhuoCode\\javaCode\\2024\\10\\file_upload\\uploads";
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String filename = req.getParameter("filename");
        File file = new File(folder, filename);
        if (file.exists()) {
            // October 十月  凯撒大帝之前，没有1月和2月，October就是8月。
            res.setContentType("application/octet-stream");
            res.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
            res.setContentLength((int)file.length());
// 192.168.0.125:8080
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[4096];
            int len;
            while ((len = fis.read(buffer)) > 0) {
                res.getOutputStream().write(buffer, 0, len);
            }
            fis.close();

        }else {
            System.out.println(filename +"：文件没找到。");
          res.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

    }
}
