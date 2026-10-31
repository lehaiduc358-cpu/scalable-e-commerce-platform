com.vanesline.ecommerce
 ├── EcommercialApplication.java (Root Package)
 │
 ├── config/          # Cấu hình Spring Security, Database, Beans...
 ├── controller/      # Tầng API, tiếp nhận HTTP Request & gửi Response
 ├── service/         # Xử lý Logic nghiệp vụ (Business Logic)
 ├── repository/      # Kết nối cơ sở dữ liệu (Spring Data JPA)
 ├── entity/          # Ánh xạ bảng Cơ sở dữ liệu (Domain/POJO Entities)
 ├── dto/             # Data Transfer Objects (Dữ liệu truyền nhận API)
 ├── exception/       # Xử lý ngoại lệ tập trung (@ControllerAdvice)
 └── util/            # Các hàm hỗ trợ tĩnh (Helper/Utility classes)