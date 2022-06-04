# HeThongQuanLyNhanKhauHoGiaDinh
# Chạy chương trình:
- Custom lại db trong file application.properties
- thêm danh sách sách tài khoàn này vào db
```
use doanphanmem1;

-- add vaitro
INSERT INTO `doanphanmem1`.`vai_tro` (`ma_vai_tro`, `code`, `ten_vai_tro`) VALUES ('1', 'ROLE_ADMIN', 'Quản trị viên');
INSERT INTO `doanphanmem1`.`vai_tro` (`ma_vai_tro`, `code`, `ten_vai_tro`) VALUES ('2', 'ROLE_POLICE', 'Công an');
INSERT INTO `doanphanmem1`.`vai_tro` (`ma_vai_tro`, `code`, `ten_vai_tro`) VALUES ('3', 'ROLE_OFFICER', 'Cán bộ');
INSERT INTO `doanphanmem1`.`vai_tro` (`ma_vai_tro`, `code`, `ten_vai_tro`) VALUES ('4', 'ROLE_USER', 'Người dùng');

select * from doanphanmem1.vai_tro;

-- add nguoi_dung
INSERT INTO `doanphanmem1`.`nguoi_dung` (`ma_nguoi_dung`,`email`, `password`, `username`, `trang_thai`) 
		VALUES (1, 'admin@gmail.com', '$2a$10$B4RlhS15uaCZ4funa93oiu2E9CNcbBwXRPljyoAydlqbfYakjF5xy', 'admin', 1);
INSERT INTO `doanphanmem1`.`nguoi_dung` (`ma_nguoi_dung`, `email`, `password`, `username`, `trang_thai`) 
		VALUES (2, 'admin123@gmail.com', '$2a$10$B4RlhS15uaCZ4funa93oiu2E9CNcbBwXRPljyoAydlqbfYakjF5xy', 'admin123', 1);
INSERT INTO `doanphanmem1`.`nguoi_dung` (`ma_nguoi_dung`, `email`, `password`, `username`, `trang_thai`) 
		VALUES (3, 'thuan@gmail.com', '$2a$10$B4RlhS15uaCZ4funa93oiu2E9CNcbBwXRPljyoAydlqbfYakjF5xy', 'thuan', 1);
INSERT INTO `doanphanmem1`.`nguoi_dung` (`ma_nguoi_dung`, `email`, `password`, `username`, `trang_thai`) 
		VALUES (4, 'congan@gmail.com', '$2a$10$B4RlhS15uaCZ4funa93oiu2E9CNcbBwXRPljyoAydlqbfYakjF5xy', 'congan', 1);

INSERT INTO `doanphanmem1`.`nguoi_dung` (`ma_nguoi_dung`, `email`, `password`, `username`, `trang_thai`) 
		VALUES (5, 'canbo@gmail.com', '$2a$10$B4RlhS15uaCZ4funa93oiu2E9CNcbBwXRPljyoAydlqbfYakjF5xy', 'canbo', 1);
select * from doanphanmem1.nguoi_dung;

-- add vai_tro_nguoi_dung
select * from doanphanmem1.vai_tro_nguoi_dung;
select * from vai_tro_nguoi_dung as vtnd 
	inner join nguoi_dung as nd on  vtnd.ma_nguoi_dung = nd.ma_nguoi_dung
	inner join vai_tro as vt on vtnd.ma_vai_tro = vt.ma_vai_tro;

INSERT INTO vai_tro_nguoi_dung (ma_nguoi_dung, ma_vai_tro) VALUES (1,1);
INSERT INTO vai_tro_nguoi_dung (ma_nguoi_dung, ma_vai_tro) VALUES (2,1);
INSERT INTO vai_tro_nguoi_dung (ma_nguoi_dung, ma_vai_tro) VALUES (3,4);
INSERT INTO vai_tro_nguoi_dung (ma_nguoi_dung, ma_vai_tro) VALUES (4,2);
INSERT INTO vai_tro_nguoi_dung (ma_nguoi_dung, ma_vai_tro) VALUES (5,3);
```
