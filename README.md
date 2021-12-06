# FTPSimulator_Client
Project lập trình mạng mô phỏng FTP Client
## Yêu cầu về chức năng phía client:
+ Đăng ký tài khoản mới, đăng nhập, cập nhật thông tin tài khoản. Thông tin tối thiểu cho mỗi 
tài khoản gồm có các trường: username (chính là địa chỉ email), password (hash), họ tên, giới 
tính, ngày sinh. Ngoài ra, địa chỉ email lúc đăng ký phải được xác thực bằng OTP (timeout 10 
phút) trước khi tài khoản đó được kích hoạt.
+ Mỗi người dùng đăng nhập được tự động đẩy đến thư mục chứa data của riêng người dùng đó.
+ Chức năng upload/download: cho phép upload/download dữ liệu lên/xuống thư mục có quyền. Có thể pause/resume quá trình upload/download.
+ Có thể đăng nhập tài khoản anonymous để upload/download vào thư mục chung.
+ Chia sẻ thư mục/file bất kỳ với quyền chỉ đọc hoặc full quyền cho một người dùng khác. Người dùng đó sẽ nhận được thông báo khi có người chia sẻ file/thư mục cho mình.
## Client Designed:
![Client_UI](https://user-images.githubusercontent.com/71429660/144884112-14846fb4-82d1-4caf-9a9e-1a4ea48380c7.jpg)
