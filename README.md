# DistributeSystemStorage
My 1st project about distribute system storage.

## Mô tả bài toán – giải pháp

1. Mô tả bài toán:
	Xây dựng ứng dụng phân tán MyStorage. Ứng dụng cho phép chia sẻ và đồng bộ dữ liệu.
	Ứng dụng xây dựng theo mô hình Client/Server. Trong đó:
		- Mỗi Client có một thư mục đồng bộ. Dữ liệu trong thư mục đó được đưa lên Server. Người dùng có thể thêm/xóa các thư mục/ tệp tin trong thư mục đồng bộ đó.
		- Server thực hiện việc đồng bộ hóa giữa các Client dựa trên 2 yếu tố đồng thời: 
		- Kích thước của file dữ liệu
		- Thời gian thay đổi file dữ liệu
		- Client tự động download và upload dữ liệu.


2. Giải pháp:
	a. RMI (Remote Method Invocation).
		RMI giúp cho việc truyền thông giữa các đối tượng phân tán được dễ dàng hơn.
		RMI là API bậc cao được xây dựng dựa trên lập trình Socket.
		Hệ thống RMI cho phép một đối tượng chạy trên một máy ảo Java này có thể kích hoạt một phương thức của một đối tượng đang chạy trên một máy ảo Java khác. Đối tượng có phương thức được gọi từ xa gọi là các đối tượng ở xa (Remote Object).
		Một ứng dụng RMI thường bao gồm 2 phần phân biệt: Một chương trình Server và một chương trình Client. 
		Chương trình Server tạo một số các Remote Object, tạo các tham chiếu (reference) đến chúng và chờ những chương trình Client kích hoạt các phương thức của các Remote Object này. 
		Chương trình Client lấy một tham chiếu đến một hoặc nhiều Remote Object trên Server và kích hoạt các phương thức từ xa thông qua các tham chiếu.
		Một chương trình Client có thể kích hoạt các phương thức ở xa trên một hay nhiều Server - sự thực thi của chương trình được trải rộng trên nhiều máy tính.

	b. NTP (Network Time Protocol).
		Server thực hiện việc đồng bộ hóa dữ liệu giữa các Client dựa vào 2 yếu tố là :
			- Kích thước của file dữ liệu
			- Thời gian thay đổi file dữ liệu
		Trong đó, để đồng bộ dựa vào thời gian thay đổi file dữ liệu, trước hết phải đồng bộ đồng hồ của của các máy Client và Server  Sử dụng giao thức NTP. Cơ chế hoạt động của NTP như sau :
			- NTP client gửi một gói tin, trong đó chứa một thẻ thời gian tới cho NTP server.
			- NTP server nhận được gói tin, gửi trả lại NTP client một gói tin khác, có thẻ thời gian là thời điểm nó gửi gói tin đó đi.
			- NTP client nhận được gói tin đó, tính toán độ trễ, dựa và thẻ thời gian mà nó nhận được cùng với độ trễ đường truyền, NTP client sẽ set lại thời gian của nó.

## Hướng dẫn cài đặt
	1. Import 2 Project vao trong Eclipse/Netbean.
	2. Cài đặt ntp: Ubuntu/Debian based: sudo apt-get install ntp 
	3. Set Policy: Right Click --> Run Configurations... --> Arguments --> VM Arguments --> Thêm -Djava.security.policy=security.policy
	4. Run!

## Tài liệu tham khảo

	- Slide môn học Các hệ phân tán của thầy Nguyễn Bình Minh
	- JavaRMI : www.ejbtutorial.com
	- Simple NTP Client and NTPMessage  -  Adam Buckley  
	- NTP Ubuntu : www.ubuntugeek.com
	- File Synchronized : www.java2s.com 
