/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import static Dao.Connect.database;
import static Dao.DangKyDao.collection;
import com.mongodb.client.MongoCollection;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bson.Document;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


/**
 *
 * @author 84862
 */
public class QuenMatKhauDao {
    
    public static MongoCollection<Document> collection;
    private static final String EMAIL_PATTERN =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    
    public QuenMatKhauDao()
    {
        collection = database.getCollection("Account");
    }
    
    public boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    public boolean isUsernameTaken( String username) {
        Document query = new Document("username", username);
        Document account = collection.find(query).first();
        return account != null;
    }
    
    public void sendEmail(String recipient, String subject, String content) {
        // Địa chỉ email người gửi và App Password
        final String senderEmail = "lieuhoanglong911@gmail.com";  // Email của bạn
        final String appPassword = "ztdq zhfg fxis jbmh";  // App Password (tạo từ Google)

        // Cấu hình các thuộc tính cho SMTP server (sử dụng Gmail)
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");  // Kích hoạt xác thực
        properties.put("mail.smtp.starttls.enable", "true");  // Kích hoạt STARTTLS
        properties.put("mail.smtp.host", "smtp.gmail.com");  // Host của Gmail
        properties.put("mail.smtp.port", "587");  // Cổng SMTP cho Gmail

        // Tạo session với thông tin xác thực
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // Sử dụng email và App Password để xác thực
                return new PasswordAuthentication(senderEmail, appPassword);
            }
        });

        try {
            // Tạo một đối tượng MimeMessage cho email
            Message message = new MimeMessage(session);

            // Đặt thông tin người gửi và người nhận
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));

            // Đặt tiêu đề và nội dung email
            message.setSubject(subject);
            message.setText(content);

            // Gửi email
            Transport.send(message);
            System.out.println("Email đã gửi thành công!");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Gửi email thất bại: " + e.getMessage());
        }
    }
    
}
