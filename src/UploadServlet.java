import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    public String upload_folder =  "E:\\zhuoCode\\javaCode\\2024\\10\\file_upload/uploads";
    public String upload_log_folder = upload_folder + "/logs";
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        Part file = request.getPart("filename");
        String submittedFileName = file.getSubmittedFileName();
        String newFileName = getNewFileName(submittedFileName);
        System.out.println(newFileName);

        File upload_dir = new File(this.upload_folder);

        if(!upload_dir.exists()){
//            mk => make      dir => directory
            boolean mkdir = upload_dir.mkdir();
            if(mkdir){
                System.out.println("uploads创建成功");
            }else {
                System.out.println("uploads创建失败");
            }
        }
        // 上传文件
        file.write(upload_folder +"/"+newFileName);
        // 192.168.0.125:8080

        // 获取ip地址
        String ip = request.getRemoteAddr();

        // 生成上传日志
        generateLog(ip,submittedFileName, newFileName);

        response.sendRedirect("/index.jsp");
    }

    private void generateLog(String ip, String submittedFileName, String newFileName) throws IOException {
        // 192.168.1.1	test.txt    zhangsan_20210323135029.txt    2021年3月23日13:50:29
        String date = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date());
        String log = ip + "\t" + submittedFileName + "\t" + newFileName + "\t" + date + "\n";

        if (!new File(upload_log_folder).exists()) {
            boolean mkdirs = new File(upload_log_folder).mkdirs();
            if (mkdirs) {
                System.out.println("logs文件夹创建成功");
            }else {
                System.out.println("logs文件夹创建失败");
            }
        }
        FileWriter logWriter = new FileWriter(upload_log_folder+"/upload.log", true);
        logWriter.write(log);
        logWriter.close();

    }

    private String getNewFileName(String submittedFileName) {

        String name = submittedFileName.substring(0,submittedFileName.lastIndexOf("."));
        String extension = submittedFileName.substring(submittedFileName.lastIndexOf("."));

        String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return name + "_" + date + extension;
    }
}
