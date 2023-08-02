USE SE1715_G4_SWP391

INSERT INTO dbo.Role(code, name, description) VALUES('Admin', N'Administrator', N'Manager webapp')
INSERT INTO dbo.Role(code, name, description) VALUES('Supplier', N'Supplier', N'provide product')
INSERT INTO dbo.Role(code, name, description) VALUES('Shipper', N'Shipper', N'Provide Shipper')
INSERT INTO dbo.Role(code, name, description) VALUES('Customer', N'Customer', N'Using webapp')

INSERT INTO dbo.Customer(googleId, userName, password, created, lastLogin, status, gender, image, firstName, lastName, address, phone, email, totalMoney)
VALUES(NULL, 'sondv', 'IBHYlgvGeq0jk588TS1yfA==', GETDATE(), GETDATE(),  1, 1,  (SELECT * FROM OPENROWSET(BULK N'C:/images/customers/default.png', SINGLE_BLOB) as T1),
N'Đào Văn', N'Sơn', N'FPT University', '09090909', 'daoson03112002@gmail.com', 100000000)

INSERT INTO dbo.Administrator(userName, password, lastLogin, isActive, roleID, email, img)
VALUES('admin', 'FG9b0v0WVGm7BaKVh6E3xw==', GETDATE(), 1, 1, 'vanson021103@gmail.com', (SELECT * FROM OPENROWSET(BULK N'C:/images/customers/default.png', SINGLE_BLOB) as T1))

INSERT INTO dbo.Shipments(CompanyName, Phone, Email, Status, createdDate, createdBy) VALUES(N'J&T Express', N'1900 1088', N'JTExpress@gmail.com',1, GETDATE(), 1)
INSERT INTO dbo.Shipments(CompanyName, Phone, Email, Status, createdDate, createdBy) VALUES(N'NINJA VAN', N'1900 886 877', N'NINJAVAN@gmail.com',1, GETDATE(), 1)
INSERT INTO dbo.Shipments(CompanyName, Phone, Email, Status, createdDate, createdBy) VALUES(N'Ahamove', N'1900 545411', N'Ahamove@gmail.com', 1, GETDATE(), 1)
INSERT INTO dbo.Shipments(CompanyName, Phone, Email, Status, createdDate, createdBy) VALUES(N'Ship60', N'1900 6362090', N'Ship60@gmail.com', 1, GETDATE(), 1)
INSERT INTO dbo.Shipments(CompanyName, Phone, Email, Status, createdDate, createdBy) VALUES(N'GHN Express', N'1900 636677', N'GHNExpress@gmail.com', 1, GETDATE(), 1)

INSERT INTO [dbo].[Supplier]([companyName],[Status],[createdDate],[createdBy],[email],[phone],[homePage])VALUES(N'PV GAS',1,CURRENT_TIMESTAMP,1,'pvgas@pvgas.com.vn','+84 28 3781 6777','https://www.pvgas.com.vn')
INSERT INTO [dbo].[Supplier]([companyName],[Status],[createdDate],[createdBy],[email],[phone],[homePage])VALUES(N'Gas Gia Đình',1,CURRENT_TIMESTAMP,1,'gasgiadinh.vn@gmail.com','(028) 37.155.166','http://gasgiadinh.vn')
INSERT INTO [dbo].[Supplier]([companyName],[Status],[createdDate],[createdBy],[email],[phone],[homePage])VALUES(N'VT Gas',1,CURRENT_TIMESTAMP,1,' info@vt-gas.com.vn','061. 383 1988','http://www.vtgas.com.vn/')

INSERT INTO [dbo].[Supplier]([companyName],[Status],[createdDate],[createdBy],[email],[phone],[homePage])VALUES('NaMilux',1,CURRENT_TIMESTAMP,1,'info@namilux.com','0389764184','https://namilux.com/vi/home')
INSERT INTO [dbo].[Supplier]([companyName],[Status],[createdDate],[createdBy],[email],[phone],[homePage])VALUES('Rinnai',1,CURRENT_TIMESTAMP,1,'info@rinnaivietnamofficial@gmail.com','(028) 6292 8184','https://rinnai.com.vn')
INSERT INTO [dbo].[Supplier]([companyName],[Status],[createdDate],[createdBy],[email],[phone],[homePage])VALUES('Windo',1,CURRENT_TIMESTAMP,1,'windothienthanh@gmail.com','0908492923','https://windo.vn')


INSERT INTO [dbo].[Category]([code],[name],[keyword],[description])VALUES('binh-gas-1',N'Bình gas','gas/binhgas/binhga/binhga12kg/binhga45kg',N'Bình gas là một thiết bị chứa gas được chế tạo từ vật liệu thép chuyên dụng để bảo quản và giữ an toàn khi lưu trữ, vận chuyển và sử dụng gas.')
INSERT INTO [dbo].[Category]([code],[name],[keyword],[description])VALUES('bep-gas-2',N'Bếp gas','gas/bepgas/bepga/bepgadoi/bepgakhe/bepgamini',N'Bếp gas là một loại bếp sử dụng nhiên liệu là khí gas (khí thiên nhiên) để nấu ăn')
INSERT INTO [dbo].[Category]([code],[name],[keyword],[description])VALUES('phu-kien-3',N'Phụ kiện','gas/bepgas/bepga/bepgadoi/bepgakhe/bepgamini',N'Phụ kiện là những bộ phận phụ trợ cho bếp ga và bình ga')

INSERT INTO [dbo].[Discount]([name],[description],[isActive],[discount],[couponCode],[startDate],[expirationDate])VALUES(N'Không giảm',N'Không giảm',0,0,'sale0',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)
INSERT INTO [dbo].[Discount]([name],[description],[isActive],[discount],[couponCode],[startDate],[expirationDate])VALUES(N'Giảm 5%',N'Giảm 5%',0,0.05,'sale5',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)
INSERT INTO [dbo].[Discount]([name],[description],[isActive],[discount],[couponCode],[startDate],[expirationDate])VALUES(N'Giảm 10%',N'Giảm 10%',0,0.1,'sale10',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)
INSERT INTO [dbo].[Discount]([name],[description],[isActive],[discount],[couponCode],[startDate],[expirationDate])VALUES(N'Giảm 15%',N'Giảm 15%',0,0.15,'sale15',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)
INSERT INTO [dbo].[Discount]([name],[description],[isActive],[discount],[couponCode],[startDate],[expirationDate])VALUES(N'Giảm 20%',N'Giảm 20%',0,0.20,'sale20',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)



INSERT [dbo].[Product] ( [code], [name], [keywords],
[shortDescription], [description],
[categoryID], [supplierId], [isActive], [unitPrice], image,[stockQuantity], [unitOnOrders], [createdDate], [createdBy], [discountId],[warranty]) VALUES
(N'gas-gia-dinh-mau-xam-1', N'Gas gia đình màu xám', N'bình gas;gia đình;xám',
N'Bình gas 12kg.', N'Bình gas chính hãng của Công ty MTV Khi đốt Gia đình (Thuộc Tập đoàn Alphapetrol ):Sản phẩm bình gas có mua Bảo hiểm cháy nổ theo quy định Nhà nước.:Vỏ bình gas sản xuất theo tiêu chuẩn DOT-4BA-240, DOT-4BW-240 và TCVN 6292-1997.:Được bán lẻ và giao hàng bởi Gas Bình Minh , Nam gas , Gas Hướng dương , Gas 40:Để cập nhật giá bán theo thời điểm hiện tại, vui lòng liên hệ Hệ thống Chi nhánh gần nhất.:Quý khách hàng có nhu cầu sử dụng với số lượng nhiều (từ 4 - 5 bình gas /tháng trở lên), vui lòng liên hệ để có Chính sách giá bán tốt nhất!:Gas Gia Đình là hệ thống cửa hàng Gas chất lượng, chuyên nghiệp đã được người tiêu dùng tin tưởng trong suốt thời gian qua, chúng tôi cung cấp đủ các thương hiệu bình gas uy tín như Gas Vimexco, Gas Petro Việt Nam, Gas Gia Đình, Gas Petrolimex…đảm bảo chất lượng kiểm định cho khách hàng lựa chọn. Khi chọn mua bình gas tại hệ thống Gas Gia Đình, quý khách còn được nhận hỗ trợ các chính sách hậu mãi, ưu đãi, chăm sóc khách hàng tốt nhất bởi Gas Bình Minh , Nam gas , Gas Hướng dương , Gas 40, đảm bảo sự hài lòng cho quý khách.',
1, 2, 1, 490000, 'images/products/1/df.jpg',203, 22, GETDATE(), 1, 1,2)


INSERT [dbo].[Product] ([code], [name], [keywords],
[shortDescription], [description],
[categoryID], [supplierId], [isActive], [unitPrice], [image], [stockQuantity], [unitOnOrders], [createdDate], [createdBy], [discountId], [warranty]) VALUES
( N'binh-gas-12-kg-2', N'Bình gas 12 Kg', N'bình gas;gas;12;12kg',
N'Đại diện phân phối các loại sản phẩm bình gas petro Vietnam Gas giá thành tố nhất tại việt nam', N'Bình gas dân dụng:Nhãn hiệu : Petro Vietnam Gas::Dung tích : 12 Lít:Loại bình  : Sắt',
1, 1, 1, 475000, 'images/products/2/df.jpg', 322, 45, GETDATE(), 1, 1, 2)




INSERT [dbo].[Product] ([code], [name], [keywords],
[shortDescription], [description],
[categoryID], [supplierId], [isActive], [unitPrice], [image], [stockQuantity], [unitOnOrders], [createdDate], [createdBy], [discountId],[warranty]) VALUES
(N'binh-gas-gia-dinh-mau-vang--3', N'Bình gas gia đình màu vàng', N'bình gas;gia đình;vàng;12kg;12',
N'Bình gas 12kg.', N'Bình gas chính hãng của Công ty MTV Khi đốt Gia đình (Thuộc Tập đoàn Alphapetrol ):Sản phẩm bình gas có mua Bảo hiểm cháy nổ theo quy định Nhà nước.:Vỏ bình gas sản xuất theo tiêu chuẩn DOT-4BA-240, DOT-4BW-240 và TCVN 6292-1997.:Được bán lẻ và giao hàng bởi Gas Bình Minh , Nam gas , Gas Hướng dương , Gas 40:Để cập nhật giá bán theo thời điểm hiện tại, vui lòng liên hệ Hệ thống Chi nhánh gần nhất.:Quý khách hàng có nhu cầu sử dụng với số lượng nhiều (từ 4 - 5 bình gas /tháng trở lên), vui lòng liên hệ để có Chính sách giá bán tốt nhất!:Gas Gia Đình là hệ thống cửa hàng Gas chất lượng, chuyên nghiệp đã được người tiêu dùng tin tưởng trong suốt thời gian qua, chúng tôi cung cấp đủ các thương hiệu bình gas uy tín như Gas Vimexco, Gas Petro Việt Nam, Gas Gia Đình, Gas Petrolimex…đảm bảo chất lượng kiểm định cho khách hàng lựa chọn. Khi chọn mua bình gas tại hệ thống Gas Gia Đình, quý khách còn được nhận hỗ trợ các chính sách hậu mãi, ưu đãi, chăm sóc khách hàng tốt nhất bởi Gas Bình Minh , Nam gas , Gas Hướng dương , Gas 40, đảm bảo sự hài lòng cho quý khách.',
1, 2, 1, 495000, 'images/products/3/df.jpg', 311, 24, GETDATE(), 1, 1,2)

INSERT [dbo].[Product] ([code], [name], [keywords],
[shortDescription], [description],
[categoryID], [supplierId], [isActive], [unitPrice], [image], [stockQuantity], [unitOnOrders], [createdDate], [createdBy], [discountId],[warranty]) VALUES
(N'binh-gas-gia-dinh-mau-do-4', N'Bình gas gia đình màu đỏ', N'bình gas;gia đình;đỏ;12kg;12',
N'Bình gas 12kg.', N'Bình gas chính hãng của Công ty MTV Khi đốt Gia đình (Thuộc Tập đoàn Alphapetrol ):Sản phẩm bình gas có mua Bảo hiểm cháy nổ theo quy định Nhà nước.:Vỏ bình gas sản xuất theo tiêu chuẩn DOT-4BA-240, DOT-4BW-240 và TCVN 6292-1997.:Được bán lẻ và giao hàng bởi Gas Bình Minh , Nam gas , Gas Hướng dương , Gas 40:Để cập nhật giá bán theo thời điểm hiện tại, vui lòng liên hệ Hệ thống Chi nhánh gần nhất.:Quý khách hàng có nhu cầu sử dụng với số lượng nhiều (từ 4 - 5 bình gas /tháng trở lên), vui lòng liên hệ để có Chính sách giá bán tốt nhất!:Gas Gia Đình là hệ thống cửa hàng Gas chất lượng, chuyên nghiệp đã được người tiêu dùng tin tưởng trong suốt thời gian qua, chúng tôi cung cấp đủ các thương hiệu bình gas uy tín như Gas Vimexco, Gas Petro Việt Nam, Gas Gia Đình, Gas Petrolimex…đảm bảo chất lượng kiểm định cho khách hàng lựa chọn. Khi chọn mua bình gas tại hệ thống Gas Gia Đình, quý khách còn được nhận hỗ trợ các chính sách hậu mãi, ưu đãi, chăm sóc khách hàng tốt nhất bởi Gas Bình Minh , Nam gas , Gas Hướng dương , Gas 40, đảm bảo sự hài lòng cho quý khách.',
1, 2, 1, 495000, 'images/products/4/df.jpg', 311, 24, GETDATE(), 1, 3,2)

INSERT [dbo].[Product] ([code], [name], [keywords],
[shortDescription], [description],
[categoryID], [supplierId], [isActive], [unitPrice], [image], [stockQuantity], [unitOnOrders], [createdDate], [createdBy], [discountId],[warranty]) VALUES
(N'binh-gas-gia-dinh-mau-xanh-vt-5', N'Bình gas gia đình màu xanh VT', N'bình gas;gia đình;xanh;12kg;12',
N'Bình gas 12kg.', N'Bình gas chính hãng của Công ty MTV Khi đốt Gia đình (Thuộc Tập đoàn Alphapetrol ):Sản phẩm bình gas có mua Bảo hiểm cháy nổ theo quy định Nhà nước.:Vỏ bình gas sản xuất theo tiêu chuẩn DOT-4BA-240, DOT-4BW-240 và TCVN 6292-1997.:Được bán lẻ và giao hàng bởi Gas Bình Minh , Nam gas , Gas Hướng dương , Gas 40:Để cập nhật giá bán theo thời điểm hiện tại, vui lòng liên hệ Hệ thống Chi nhánh gần nhất.:Quý khách hàng có nhu cầu sử dụng với số lượng nhiều (từ 4 - 5 bình gas /tháng trở lên), vui lòng liên hệ để có Chính sách giá bán tốt nhất!:Gas Gia Đình là hệ thống cửa hàng Gas chất lượng, chuyên nghiệp đã được người tiêu dùng tin tưởng trong suốt thời gian qua, chúng tôi cung cấp đủ các thương hiệu bình gas uy tín như Gas Vimexco, Gas Petro Việt Nam, Gas Gia Đình, Gas Petrolimex…đảm bảo chất lượng kiểm định cho khách hàng lựa chọn. Khi chọn mua bình gas tại hệ thống Gas Gia Đình, quý khách còn được nhận hỗ trợ các chính sách hậu mãi, ưu đãi, chăm sóc khách hàng tốt nhất bởi Gas Bình Minh , Nam gas , Gas Hướng dương , Gas 40, đảm bảo sự hài lòng cho quý khách.',
1, 2, 1, 495000, 'images/products/5/df.jpg', 311, 24, GETDATE(), 1, 1,2)


INSERT [dbo].[Product] ([code], [name], [keywords],
[shortDescription], [description],
[categoryID], [supplierId], [isActive], [unitPrice], [image], [stockQuantity], [unitOnOrders], [createdDate], [createdBy], [discountId],[warranty]) VALUES
(N'binh-gas-gia-dinh-45-kg-6', N'Bình gas gia đình 45Kg', N'bình gas;gia đình;45kg;45',
N'Bình gas 45Kg.', N'Bình gas chính hãng của Công ty MTV Khi đốt Gia đình (Thuộc Tập đoàn Alphapetrol ):Sản phẩm bình gas có mua Bảo hiểm cháy nổ theo quy định Nhà nước.:Vỏ bình gas sản xuất theo tiêu chuẩn DOT-4BA-240, DOT-4BW-240 và TCVN 6292-1997.:Được bán lẻ và giao hàng bởi Gas Bình Minh , Nam gas , Gas Hướng dương , Gas 40:Để cập nhật giá bán theo thời điểm hiện tại, vui lòng liên hệ Hệ thống Chi nhánh gần nhất.:Quý khách hàng có nhu cầu sử dụng với số lượng nhiều (từ 4 - 5 bình gas /tháng trở lên), vui lòng liên hệ để có Chính sách giá bán tốt nhất!:Gas Gia Đình là hệ thống cửa hàng Gas chất lượng, chuyên nghiệp đã được người tiêu dùng tin tưởng trong suốt thời gian qua, chúng tôi cung cấp đủ các thương hiệu bình gas uy tín như Gas Vimexco, Gas Petro Việt Nam, Gas Gia Đình, Gas Petrolimex…đảm bảo chất lượng kiểm định cho khách hàng lựa chọn. Khi chọn mua bình gas tại hệ thống Gas Gia Đình, quý khách còn được nhận hỗ trợ các chính sách hậu mãi, ưu đãi, chăm sóc khách hàng tốt nhất bởi Gas Bình Minh , Nam gas , Gas Hướng dương , Gas 40, đảm bảo sự hài lòng cho quý khách.',
1, 2, 1, 560000, 'images/products/6/df.jpg', 524, 13, GETDATE(), 1, 1,2)



INSERT [dbo].[Product] ([code], [name], [keywords],
[shortDescription], [description],
[categoryID], [supplierId], [isActive], [unitPrice], [image], [stockQuantity], [unitOnOrders], [createdDate], [createdBy], [discountId],[warranty]) VALUES
(N'binh-do-van-pol-7', N'Bình đỏ - Van POL', N'bình;bình gas;đỏ;POL',
N'Bình gas màu đỏ van POL', N'1.	Tiêu chuẩn	DOT 4BA-240:2.	Dung tích nước	26.2 ~ 26.5 lít (±0.2):3.	Dung lượng chứa LPG	12 kg:4.	Tiêu chuẩn thép	JIS G 3116 SG 255:5.	Đường kính ngoài của bình	300mm (±2):6.	Chiều cao tổng thể của bình	560mm (±5):7.	Áp suất thử thủy tĩnh	34 Kg/cm2:8.	Van	POL V4E hiệu SCG',
1, 3, 1, 500000, 'images/products/7/df.jpg', 432, 75, GETDATE(), 1, 1,2)

INSERT [dbo].[Product] ([code], [name], [keywords],
[shortDescription], [description],
[categoryID], [supplierId], [isActive], [unitPrice], [image], [stockQuantity], [unitOnOrders], [createdDate], [createdBy], [discountId],[warranty]) VALUES
(N'binh-12-kg-van-compact-8', N'Bình 12kg van Compact', N'bình;bình gas;compact',
N'Bình gas van thuộc loại nhỏ gọn', N' 1.	 Tiêu chuẩn	 DOT 4BA-240:2.	 Dung tích nước	 26.4 lít (±0.2):3.	 Dung lượng chứa LPG	 12 kg:4.	 Tiêu chuẩn thép	 JIS G 3116 SG 255:5.	 Đường kính ngoài của bình	 300mm (±1):6.	 Chiều cao tổng thể của bình 	 585mm (±3):7.	 Áp suất thử thủy tĩnh	 34 Kg/cm2:8.	 Van	COMPACT',
1, 3, 1, 520000, 'images/products/8/df.jpg', 313, 55, CAST(N'2020-01-22T08:37:27.990' AS DateTime), 1, 1,2)

INSERT [dbo].[Product] ([code], [name], [keywords],
[shortDescription], [description],
[categoryID], [supplierId], [isActive], [unitPrice], [image], [stockQuantity], [unitOnOrders], [createdDate], [createdBy], [discountId],[warranty]) VALUES
(N'binh-xam-ban-pol-9', N'Bình xám - Van POL', N'bình;bình gas;xám;POL',
N'Bình gas àu xám van POL', N' 1.	 Tiêu chuẩn	 DOT 4BA-240:2.	 Dung tích nước	 26.4 lít (±0.2):3.	 Dung lượng chứa LPG	 12 kg:4.	 Tiêu chuẩn thép	 JIS G 3116 SG 255:5.	 Đường kính ngoài của bình	 300mm (±1):6.	 Chiều cao tổng thể của bình 	 585mm (±3):7.	 Áp suất thử thủy tĩnh	 34 Kg/cm2:8.	 Van	 POL V87 hiệu SCG',
1, 3, 1, 500000, 'images/products/9/df.jpg', 345, 31, CAST(N'2020-01-22T08:37:27.990' AS DateTime), 1, 1,2)


INSERT [dbo].[Product] ([code], [name], [keywords],
[shortDescription], [description],
[categoryID], [supplierId], [isActive], [unitPrice], [image], [stockQuantity], [unitOnOrders], [createdDate], [createdBy], [discountId],[warranty]) VALUES
(N'binh-45-kg-loai-1-van-10', N'Bình 45kg loại 1 van', N'bình;bình gas;45;45kg;1 van',
N'Bình gas 45Kg 1 van', N'1.	 Tiêu chuẩn thiết kế	 DOT-4BW-240:2.	 Áp suất thiết kế	 240 Psi; 17 Kg/cm2:3.	 Áp suất kiểm tra thủy lực	 480 Psi; 34 Kg/cm2:4.	 Thể tích bình	 99 lít ± 0.5%:5.	 Trọng lượng khí	 45kg Bu + Pro:6.	 Đường kính ngoài thân bình         	 374mm ± 1mm:7.	 Chiều cao toàn bộ bình	 1175mm ± 5mm:8.	 Van	 POL-V87Q3 hiệu SCG',
1, 3, 1, 440000, 'images/products/10/df.jpg', 1128, 86, CAST(N'2020-01-22T08:37:27.990' AS DateTime), 1, 2, 2)




INSERT [dbo].[Product] ([code], [name], [keywords],
[shortDescription], [description],
[categoryID], [supplierId], [isActive], [unitPrice], [image], [stockQuantity], [unitOnOrders], [createdDate], [createdBy], [discountId],[warranty]) VALUES
(N'binh-gas-45-kg-loai-2-van-11', N'Bình Gas 45kg loại 2 van', N'bình;bình gas;45kg;2 van',
N'Bình gas 45kg loại 2 van', N' 1.	 Tiêu chuẩn	 DOT 4BA-240: 2.	 Dung tích nước	 26.4 lít (±0.2): 3.	 Dung lượng chứa LPG	 12 kg: 4.	 Tiêu chuẩn thép	 JIS G 3116 SG 255: 5.	 Đường kính ngoài của bình	 300mm (±1): 6.	 Chiều cao tổng thể của bình 	 585mm (±3): 7.	 Áp suất thử thủy tĩnh	 34 Kg/cm2: 8.	 2 Van	 POL V87 hiệu SCG',
1, 4, 1, 500000, 'images/products/11/df.jpg', 386, 61, CAST(N'2020-01-22T08:37:27.990' AS DateTime), 1, 2,2)


INSERT [dbo].[Product] ([code], [name], [keywords],
[shortDescription], [description],
[categoryID], [supplierId], [isActive], [unitPrice], [image], [stockQuantity], [unitOnOrders], [createdDate], [createdBy], [discountId],[warranty]) VALUES
(N'lon-gas-nhap-khau-12', N'Lon gas nhập khẩu', N'lon;lon gas;nhập khẩu',
N'Lon gas dùng cho bếp mini', N'Model	Lon gas mini NaMilux:Khối lượng tịnh	250g:Tổng khối lượng của khí nén và bình	350g:Thế tích thực	520ml',
1, 4, 1, 20000, 'images/products/12/df.jpg', 43120, 2857, CAST(N'2020-01-22T08:37:27.990' AS DateTime), 1, 1,2)





INSERT [dbo].[Product] ([code], [name], [keywords],
[shortDescription], [description],
[categoryID], [supplierId], [isActive], [unitPrice], [image], [stockQuantity], [unitOnOrders], [createdDate], [createdBy], [discountId],[warranty]) VALUES
(N'bao-ro-ri-gas-13', N'BÁO RÒ RỈ GAS', N'báo;rò rỉ',
N'Đơn vị chuyên nhập khẩu, phân phối sỉ và lẻ các thiết bị Gas, van Gas an toàn, hệ thống gas trung tâm, hệ thống gas công nghiệp, máy báo rò rỉ gas, dây gas… đảm bảo tuyệt đối an toàn khi sử dụng.:Thiết bị báo rò rỉ gas đã trở nên khá quen thuộc với nhiều nhà bếp nhà hàng, bếp công nghiệp và cả bếp của các gia đình hiện nay. Công dụng chính của thiết bị gas này là phát hiện khí gas bị rò rỉ và báo động khi có các sự cố về rò rỉ gas, đảm bảo an toàn trong nhà bếp.::Đặc biệt là trong các nhà hàng thì các thiết bị bếp hoặc van dây được sử dụng với cường độ cao & thường xuyên thì việc xì gas hay rò rỉ gas rất dễ xảy ra. Trong nhiều trường hợp khác nguyên nhân xảy ra các sự cố về gas cũng có thể là do vết dầu mỡ bắn vào lâu ngày làm mục dây gas hoặc chuột bọ cắn dây gas, vì vậy việc trang bị cho nhà bếp một thiết bị báo rò rỉ gas này là khá quan trọng và đảm bảo an toàn cho cả người và tài sản của nhà hàng.::Thiết bị báo rò rỉ gas sẽ phát huy tác dụng tốt nhất khi kết hợp với thiết bị ngắt gas tự động và còi hú báo động, khi có sự cố thì thiết bị này sẽ truyền tín hiệu đến còi hú để báo động sự cố và thiết bị ngắt gas cũng tự động ngắt gas ngăn không cho gas tiếp tục rò rỉ ra ngoài.::Hiện nay trên thị trường thì các thiết bị báo rò rỉ gas khá đa dạng về nguồn gốc xuất xứ như Hàn Quốc, Trung Quốc, Đài Loan, Nhật Bản…Tùy theo ngân sách và nhu cầu của từng nhà hàng thì có thể sắm thiết bị cho phù hợp.::Toàn phát là đơn vị chuyên nhập khẩu và phân phối các thiết bị báo rò rỉ gas an toàn của Hàn Quốc, Nhật Bản, Hàn Quốc. chúng tôi sẵn sàng tư vấn cho quý vị lựa chọn thiết bị phù hợp, lắp đặt tận nơi, hướng dẫn sử dụng và bảo hành bảo trì chu đáo cho các sản phẩm bán ra.', N'Model	VTD 2005 (AC type):Nhận biết loại khí	LPG, LNG, Khí Mêtan, Khí dễ cháy:Điểm cảnh báo	Nồng độ cảnh báo 25% LEL:(Điểm cài đặt 18% LEL)::Công nghệ phát hiện	Khuyếch tán và phân tích chất dế cháy:Thời gian kích hoạt	Trong vòng 20 giây:Nhiệt độ và độ ẩm vận hành	0℃~40℃. < 90% (RH):Nguồn điện	AC 220V. 50/60Hz:Tiêu thụ điện năng	1.5W:Trọng lượng và Kích thước	224g và 70x120x38mm:Cảnh báo	 Đèn LED màu vàng:Phát âm báo (70dB DC 12V 20mA):ABS Nhựa chống cháy',
3, 1, 1, 200000, 'images/products/13/df.jpg', 3151, 245, CAST(N'1969-04-22T08:37:27.990' AS DateTime), 1, 1,24)

INSERT [dbo].[Product] ([code], [name], [keywords],
[shortDescription], [description],
[categoryID], [supplierId], [isActive], [unitPrice], [image], [stockQuantity], [unitOnOrders], [createdDate], [createdBy], [discountId],[warranty]) VALUES
(N'bao-ro-ri-gas-jic-678n-14', N'BÁO RÒ RỈ GAS JIC-678N', N'báo;rò rỉ;JIC-678N',
N'', N'Thiết bị báo rò rỉ gas đã trở nên khá quen thuộc với nhiều nhà bếp nhà hàng, bếp công nghiệp và cả bếp của các gia đình hiện nay. Công dụng chính của thiết bị gas này là phát hiện khí gas bị rò rỉ và báo động khi có các sự cố về rò rỉ gas, đảm bảo an toàn trong nhà bếp.:Đặc biệt là trong các nhà hàng thì các thiết bị bếp hoặc van dây được sử dụng với cường độ cao & thường xuyên thì việc xì gas hay rò rỉ gas rất dễ xảy ra. Trong nhiều trường hợp khác nguyên nhân xảy ra các sự cố về gas cũng có thể là do vết dầu mỡ bắn vào lâu ngày làm mục dây gas hoặc chuột bọ cắn dây gas, vì vậy việc trang bị cho nhà bếp một thiết bị báo rò rỉ gas này là khá quan trọng và đảm bảo an toàn cho cả người và tài sản của nhà hàng.:Thiết bị báo rò rỉ gas sẽ phát huy tác dụng tốt nhất khi kết hợp với thiết bị ngắt gas tự động và còi hú báo động, khi có sự cố thì thiết bị này sẽ truyền tín hiệu đến còi hú để báo động sự cố và thiết bị ngắt gas cũng tự động ngắt gas ngăn không cho gas tiếp tục rò rỉ ra ngoài.:Hiện nay trên thị trường thì các thiết bị báo rò rỉ gas khá đa dạng về nguồn gốc xuất xứ như Hàn Quốc, Trung Quốc, Đài Loan, Nhật Bản…Tùy theo ngân sách và nhu cầu của từng nhà hàng thì có thể sắm thiết bị cho phù hợp.:Toàn phát là đơn vị chuyên nhập khẩu và phân phối các thiết bị báo rò rỉ gas an toàn của Hàn Quốc, Nhật Bản, Hàn Quốc. chúng tôi sẵn sàng tư vấn cho quý vị lựa chọn thiết bị phù hợp, lắp đặt tận nơi, hướng dẫn sử dụng và bảo hành bảo trì chu đáo cho các sản phẩm bán ra.:Ngoài ra Petro VN cũng là nhà thầu::Lắp đặt mới, thay thế các thiết bị đã cũ, bảo trì bảo dưỡng hệ thống gas, ống gas, van dây gas của các nhà hàng đã sử dụng lâu ngày & có nguy cơ bị xì gas hoặc không đảm bảo an toàn khi có các sự cố về Gas.:Nhận tư vấn thiết kế thi công hệ thống gas an toàn tuyệt đối khi sử dụng cho nhà bếp nhà hàng, bếp công nghiệp căng tin trường học, bệnh viện nhà máy khu chế xuất…',
3, 1, 1, 195000, 'images/products/14/df.jpg', 2041, 25, CAST(N'1969-04-22T08:37:27.990' AS DateTime), 1, 1, 24)

INSERT [dbo].[Product] ([code], [name], [keywords],
[shortDescription], [description],
[categoryID], [supplierId], [isActive], [unitPrice], [image], [stockQuantity], [unitOnOrders], [createdDate], [createdBy], [discountId],[warranty]) VALUES
(N'bo-ngat-gas-tu-dong-15', N'BỘ NGẮT GAS TỰ ĐỘNG', N'ngắt gas;tư động',
N'Bộ ngắt gas tự động với hệ thống phát hiện rò rỉ gas', N'– Nguồn điện : AC-110/220V 50/60HZ:– Điện năng tiêu thụ chờ : 0.5W:– Điện năng tiêu thụ báo động : 2W:– Báo động tập trung : LEL 1/4:– Hoạt động nhiệt độ : -40oC ~ 70oC:– Loại: Bộ ngắt gas tự động với hệ thống phát hiện rò rỉ gas:– Trọng lượng: 0,15 kg:– Loại điều khiển: Thời gian (30 giây):-Tính năng chuyển mạch từ xa và tự động ngắt khi phát hiện rò rỉ khí gas',
3, 1, 1, 220000, 'images/products/15/df.jpg', 4114, 422, CAST(N'1969-04-22T08:37:27.990' AS DateTime), 1, 1, 24)



INSERT [dbo].[Product] ([code], [name], [keywords],
[shortDescription], [description],
[categoryID], [supplierId], [isActive], [unitPrice], [image], [stockQuantity], [unitOnOrders], [createdDate], [createdBy], [discountId],[warranty]) VALUES
(N'day-dan-gas-cao-ap-cong-nghiep-windo-16', N'Dây dẫn gas cao áp công nghiệp WINDO', N'dây dẫn;cáo áp',
N'sdesc', N'Dây dẫn gas công nghiệp là sản phẩm đầu ra áp suất cao dùng cho van bếp gas công nghiệp khè nấu nhanh có độ bền rất cao,được người việt tin dùng toàn quốc. -Dây gas sử dụng cho van gas công nghiệp dùng bình gas 12kg hoặc bình gas bò 45kg - 50kg. -Sử dụng cao su chuyên dụng NBR ( nhật bản ) -->có độ bóng,không mùi,không bị lão hoá.',
3, 6, 1, 150000, 'images/products/16/df.jpg', 1000, 311, CAST(N'1969-04-22T08:37:27.990' AS DateTime), 1, 1, 24)

INSERT [dbo].[Product] ([code], [name], [keywords],
[shortDescription], [description],
[categoryID], [supplierId], [isActive], [unitPrice], [image], [stockQuantity], [unitOnOrders], [createdDate], [createdBy], [discountId],[warranty]) VALUES
(N'van-dieu-ap-ngat-gas-tu-dong-cao-ap-windo-wd-349-17', N'VAN ĐIỀU ÁP NGẮT GAS TỰ ĐỘNG CAO ÁP WINDO WD-349', N'van;van điều áp;ngắt gas;tự động;windo;wd-349',
N'VAN ĐIỀU ÁP NGẮT GAS TỰ ĐỘNG CAO ÁP WINDO WD-349', N'desc',
3, 6, 1, 70000, 'images/products/17/df.jpg', 422, 43, CAST(N'1969-04-22T08:37:27.990' AS DateTime), 1, 1, 24)

INSERT [dbo].[Product] ([code], [name], [keywords],
[shortDescription], [description],
[categoryID], [supplierId], [isActive], [unitPrice], [image], [stockQuantity], [unitOnOrders], [createdDate], [createdBy], [discountId],[warranty]) VALUES
(N'van-dieu-ap-ngat-gas-tu-dong-cao-ap-windo-wd-348-18', N'VAN ĐIỀU ÁP NGẮT GAS TỰ ĐỘNG CAO ÁP WINDO WD-348', N'van;van điều áp;ngắt gas;tự động;windo;wd-348',
N'VAN ĐIỀU ÁP NGẮT GAS TỰ ĐỘNG CAO ÁP WINDO WD-348', N'desc',
3, 6, 1, 70000, 'images/products/18/df.jpg', 111, 64, CAST(N'1969-04-22T08:37:27.990' AS DateTime), 1, 1, 24)




INSERT [dbo].[Product] ([code], [name], [keywords],
[shortDescription], [description],
[categoryID], [supplierId], [isActive], [unitPrice], [image], [stockQuantity], [unitOnOrders], [createdDate], [createdBy], [discountId],[warranty]) VALUES
(N'van-dieu-ap-srg-19', N'Van điều áp SRG', N'Van điều áp;SRG',
N'Van điều áp đảm bảo đường truyền gas ổn định, an toàn', N'',
3, 3, 1, 70000, 'images/products/19/df.jpg', 4380, 43, CAST(N'1969-04-22T08:37:27.990' AS DateTime), 1, 1, 24)

INSERT [dbo].[Product] ([code], [name], [keywords],
[shortDescription], [description],
[categoryID], [supplierId], [isActive], [unitPrice], [image], [stockQuantity], [unitOnOrders], [createdDate], [createdBy], [discountId],[warranty]) VALUES
(N'van-dieu-ap-comap-20', N'Van điều áp Comap', N'Van điều áp;Comap',
N'Van điều áp đảm bảo đường truyền gas ổn định, an toàn', N'',
3, 3, 1, 80000, 'images/products/20/df.jpg', 6423, 222, CAST(N'1969-04-22T08:37:27.990' AS DateTime), 1, 1, 24)

INSERT [dbo].[Product] ([code], [name], [keywords],
[shortDescription], [description],
[categoryID], [supplierId], [isActive], [unitPrice], [image], [stockQuantity], [unitOnOrders], [createdDate], [createdBy], [discountId],[warranty]) VALUES
(N'van-dieu-ap-reca-21', N'Van điều áp Reca', N'Van điều áp;Reca',
N'Van điều áp đảm bảo đường truyền gas ổn định, an toàn', N'',
3, 3, 1, 65000, 'images/products/21/df.jpg', 3423, 43, CAST(N'1969-04-22T08:37:27.990' AS DateTime), 1, 1, 6)


INSERT [dbo].[Product] ([code], [name], [keywords],
[shortDescription], [description],
[categoryID], [supplierId], [isActive], [unitPrice], [image], [stockQuantity], [unitOnOrders], [createdDate], [createdBy], [discountId],[warranty]) VALUES
(N'vong-chan-gio-the-he-moi-22', N'VÒNG CHẮN GIÓ THẾ HỆ MỚI', N'chắn gió',
N'', N'Vòng kiềng chắn gió thế hệ mới, sử dụng cho bếp gas mini của NaMilux.:Khả nặng chịu tải lên đến 25kg:Giúp che chắn ngọn lửa không bị gió khi ở ngoài trời.:Giúp tăng hiệu suất đốt (giảm thoát nhiệt) tiết kiệm thời gian nấu lên đến 36%. Và đồng thời tiết kiệm gas 36%:Lưu ý: chỉ sử dụng môi trường ngoài trời nơi có gió lùa. Nếu sử dụng trong phòng thì nhiệt độ tụ quá cao có thể ảnh hưởng đến độ bền của đầu đốt.',
3, 4, 1, 100000, 'images/products/22/df.jpg', 56365, 3256, CAST(N'1969-04-22T08:37:27.990' AS DateTime), 1, 1, 6)


INSERT [dbo].[Product] ([code], [name], [keywords],
[shortDescription], [description],
[categoryID], [supplierId], [isActive], [unitPrice], [image], [stockQuantity], [unitOnOrders], [createdDate], [createdBy], [discountId],[warranty]) VALUES
(N'den-kho-na-197-23', N'Đèn khò NA-197', N'đèn khò;máy khò;NA-197',
N'Đèn khò đa năng dùng để hàn gắn, nung nóng, nấu ăn', N'Loại sản phẩm	Đèn khò gas:Model	NA -197:Gas sử dụng	Lon gas butane:Công suất	1.8 kW (1500 kcal/h)"Lượng gas tiêu thụ	132 g/h"Nhiệt độ đầu khò	Lên đến 1200℃:Trọng lượng	153g:Kích thước	184 x 64 x 40 mm',
3, 4, 1, 200000, 'images/products/23/df.jpg', 1239, 53, CAST(N'1969-04-22T08:37:27.990' AS DateTime), 1, 1,3)


INSERT [dbo].[Product] ([code], [name], [keywords],
[shortDescription], [description],
[categoryID], [supplierId], [isActive], [unitPrice], [image], [stockQuantity], [unitOnOrders], [createdDate], [createdBy], [discountId],[warranty]) VALUES
(N'den-kho-ts1719rn-24', N'Đèn khò TS1719RN', N'đèn khò;máy khò;TS1719RN',
N'Đèn khò đa năng dùng để hàn gắn, nung nóng, nấu ăn', N'Nấu ăn và phục vụ ăn uống:Đốt côn trùng, cỏ, rác.:Nấu cháy nhựa, uốn ống nhựa:Các công việc tạo hình trên kim loại:Loại sản phẩm	Đèn khò gas:Model	TS1719RN:Gas sử dụng	Lon gas butane:Công suất	1.4 kW (1200 Kcal/giờ):Lượng gas tiêu thụ	100g/giờ:Nhiệt độ đầu khò	>1200℃',
3, 4, 1, 200000, 'images/products/24/df.jpg', 3322, 433, CAST(N'1969-04-22T08:37:27.990' AS DateTime), 1, 1,3)

insert into Product ([code], [name], [keywords],
[shortDescription], [description],
[categoryID], [supplierId], [isActive], [unitPrice], [image], [stockQuantity], [unitOnOrders], [createdDate], [createdBy], [discountId],[warranty])
VALUES (N'bep-gas-namilux-nh-041pf-25', N'Bếp gas Namilux NH-041PF', N'Namilux NH-041PF', N'Bếp gas đơn, nhỏ gọn, tiết kiệm không gian', N'Trang bị van Inlinecut, ngắt gas tự động, an toàn khi dùng. Hệ thống đánh lửa Magneto cho lửa nhanh, tiết kiệm gas. Đầu đốt bằng hợp kim Nhôm có độ bền cao. Kiềng và vòng chắn gió bằng Inox, bền bỉ',
2, 4, 1, 299000, 'images/products/25/df.jpg', 50, 2,  CAST(N'2023-05-13T07:00:00.000' AS DateTime), 1, 1,24)

INSERT into Product  ([code], [name], [keywords],
[shortDescription], [description],
[categoryID], [supplierId], [isActive], [unitPrice], [image], [stockQuantity], [unitOnOrders], [createdDate], [createdBy], [discountId],[warranty])
VALUES (N'bap-gas-mini-namilux-nh-p3212ps-26', N'Bếp gas mini Namilux NH-P3212PS', N'Namilux NH-P3212PS', N'Thân bếp được thiết kế thông gió 4 mặt ngăn ngừa nguy cơ gây nổ lon gas', N'Nắp đậy lon gas và kiềng có hệ thống dẫn nước chống nước không tràn vào lon gas. Kích thước bếp bằng 1/2 bếp mini thông thường, phù hợp khi sử dụng trên bàn ăn nhỏ. Hệ thống đầu đốt có lưới điều tiết gas nâng cao hiệu suất, tiết kiệm thời gian đun nấu',
2, 4, 1, 340000, 'images/products/26/df.jpg', 100, 500,  CAST(N'2022-07-20T09:30:10.460' AS DateTime), 1, 1, 24)

insert into Product ([code], [name], [keywords],
[shortDescription], [description],
[categoryID], [supplierId], [isActive], [unitPrice], [image], [stockQuantity], [unitOnOrders], [createdDate], [createdBy], [discountId],[warranty])
VALUES (N'bep-khe-na-196-27', N'BẾP KHÈ NA-196', N'NA-196', N'Là sản phẩm bếp khè đầu tiên của NaMilux. Đầu đốt đúc gang nên độ bền cực kỳ cao.', N'Ngoài ra, bếp khè NA-196 có tính năng đánh lửa tự động, và điều chỉnh lửa bằng 1 tay. Bếp khè đang trở thành 1 sản phẩm thông dụng với các hộ gia đình vì Công suất cao, Nấu nhanh, Giá thành rẻ, Dễ vệ sinh',
2, 4, 1, 599000, 'images/products/27/df.jpg', 10, 20,  CAST(N'2021-05-13T09:50:42.460' AS DateTime), 1, 1, 24)

insert into Product ([code], [name], [keywords],
[shortDescription], [description],
[categoryID], [supplierId], [isActive], [unitPrice], [image], [stockQuantity], [unitOnOrders], [createdDate], [createdBy], [discountId],[warranty])
VALUES (N'bep-ga-doi-rinnai-rv-mc27be-28', N'Bếp ga đôi Rinnai RV-MC27BE', N'RV-MC27BE', N'Bếp ga đôi vận hành với lượng ga tiêu thụ 0.42 kg/h/lò.', N'Bếp ga Rinnai RV-MC27BE là loại bếp cơ bản với 2 vùng nấu dễ sử dụng, trang bị hệ thống đánh lửa Magneto bền và dễ dùng đáp ứng nhu cầu nấu nướng cơ bản của người dùng.',
2, 2, 1, 1030000, 'images/products/28/df.jpg', 50, 2,  CAST(N'2023-08-30T11:20:00.460' AS DATETIME), 1, 1, 24)

INSERT into Product  ([code], [name], [keywords],
[shortDescription], [description],
[categoryID], [supplierId], [isActive], [unitPrice], [image], [stockQuantity], [unitOnOrders], [createdDate], [createdBy], [discountId],[warranty])
VALUES (N'bep-ga-doi-rinnai-rv-715slim-29', N'Bếp ga đôi Rinnai RV-715Slim', N'RV-715Slim', N'Thiết kế hiện đại, màu đen sang trọng làm nổi bật không gian bếp.', N'Bếp ga Rinnai RV-715Slim(GL-SC) thiết kế hiện đại, màu đen sang trọng, hệ thống đánh lửa Magneto có độ bền cao, sản phẩm của thương hiệu Nhật Bản, uy tín, an toàn khi sử dụng.',
2, 5, 1, 2570000, 'images/products/29/df.jpg', 20, 20,  CAST(N'2022-07-20T12:50:00.000' AS DateTime), 1, 1, 24)

INSERT into Product  ([code], [name], [keywords],
[shortDescription], [description],
[categoryID], [supplierId], [isActive], [unitPrice], [image], [stockQuantity], [unitOnOrders], [createdDate], [createdBy], [discountId],[warranty])
VALUES (N'bep-cong-nghiep-sao-rinnai-tl-289ri-30', N'Bếp Công Nghiệp Sào Rinnai TL 289RI', N'Rinnai TL 289RI', N'đánh lửa cở học ( Magneto )', N'khung sườn bằng gang đúc nguyên khối, kiềng đầu đốt họng đốt bằng gang, điều khiển nút xoay',
2, 5, 1, 950000, 'images/products/30/df.jpg', 10, 20,  CAST(N'2023-05-13T09:50:42.460' AS DateTime), 1, 1, 24)

insert into Product ([code], [name], [keywords],
[shortDescription], [description],
[categoryID], [supplierId], [isActive], [unitPrice], [image], [stockQuantity], [unitOnOrders], [createdDate], [createdBy], [discountId],[warranty])
VALUES (N'bep-gas-doi-de-ban-mat-kinh-windo-719gl-a-31', N'BẾP GAS ĐÔI ĐỂ BÀN MẶT KÍNH WINDO 719GL-A', N'WINDO 719GL-A', N'Thiết kế mặt kính chịu lực, chịu nhiệt tốt', N'Chất liệu kính hạn chế tối đa tình trạng nứt, vỡ bề mặt khi có tác động từ bên ngoài. Đồng thời, mặt kính hạn chế chống xước và bám dính thực phẩm, sau khi đun nấu chỉ cần lấy khăn ẩm nhẹ nhàng lau là mặt bếp lại sáng bóng như mới.',
2, 6, 1, 499000, 'images/products/31/df.jpg', 50, 2,  CAST(N'2020-04-13T06:30:00.000' AS DateTime), 1, 1, 24)

INSERT into Product ([code], [name], [keywords],
[shortDescription], [description],
[categoryID], [supplierId], [isActive], [unitPrice], [image], [stockQuantity], [unitOnOrders], [createdDate], [createdBy], [discountId],[warranty])
VALUES (N'bep-doi-mat-kinh-de-ban-windo-717-2-32', N'Bếp đôi mặt kính để bàn Windo 717-2', N'Windo 717-2', N'Mặt bếp chống trầy tốt, chịu lực, chịu nhiệt tốt', N'Sử dụng ống điếu (thiết kế theo nguyên lý khí động học) đạt tiêu chuẩn JIA (Japan), hiệu suất đốt tối ưu, tiết kiệm 10% gas tiêu thụ', 
2, 6, 1, 449000, 'images/products/32/df.jpg', 50, 2,  CAST(N'2023-05-13T20:45:00.000' AS DateTime), 1, 1, 24)

insert into Product ([code], [name], [keywords],
[shortDescription], [description],
[categoryID], [supplierId], [isActive], [unitPrice], [image], [stockQuantity], [unitOnOrders], [createdDate], [createdBy], [discountId],[warranty])
VALUES (N'bep-gas-khe-cao-cap-windo-6b-33', N'BẾP GAS KHÈ CAO CẤP WINDO 6B', N'WINDO 6B', N'Toàn thân bằng gang đúc nguyên khối, Bộ hòa khí ngoại nhập', N'cho hiệu năng cao cùng lửa  to và mạnh, giúp việc chế biến các món Á, món xào với lửa lớn, món chiên, nước sốt… trở nên đơn giản hơn bao giờ hết. Mặc dù cho hiệu suất cao với lửa  nhưng bếp tiêu tốn gas không đáng kể chỉ 720g/h. Sử dụng hệ thống đánh lửa Magneto tiên tiến, lên lửa cực nhanh và không phải lo thay pin bất tiện như đánh lửa IC. Số vòng lửa có nhiều mức để người dùng có thể tùy ý chỉnh theo nhu cầu nấu nướng. Sử dụng bếp gas công nghiệp là giải pháp tiết kiệm chi phí, tiện lợi, an toàn (nếu sử dụng đúng cách) cho các nhà hàng, quán ăn.',
2, 6, 1, 899000, 'images/products/33/df.jpg', 10, 20,  CAST(N'2023-05-13T15:35:00.000' AS DateTime), 1, 1, 24)



INSERT [dbo].[Customer] ([userName], [password], [created], [lastLogin], [status], [gender], [firstName], [lastName], [address], [phone], [email]) VALUES (N'Jenna222', N'8596Q3HBQ21FYX110B7QF2ZB9Q8Y1', CAST(N'1963-04-20T01:49:19.840' AS DateTime), CAST(N'1968-02-17T02:50:29.220' AS DateTime), 0, 0, N'Sonja', N'Bradford', N'363 Green Fabien Boulevard', N'4622076042', N'qzmpj@zbzeeh.vvoasw.com')
INSERT [dbo].[Customer] ([userName], [password], [created], [lastLogin], [status], [gender], [firstName], [lastName], [address], [phone], [email]) VALUES (N'Bruce11', N'QHLRRJIQN8M5UBRE0LW4FFJ0965YXZVI9ECJT66HI9NZ4A7OKUQM2XWAXLHLEKTNC3WFZ', CAST(N'1986-12-01T01:26:08.230' AS DateTime), CAST(N'1981-06-26T03:21:45.960' AS DateTime), 0, 0, N'Johnathan', N'George', N'23 East Green Fabien Blvd.', N'7947675162', N'vqfcvcgs2@f-ica-.org')
INSERT [dbo].[Customer] ([userName], [password], [created], [lastLogin], [status], [gender], [firstName], [lastName], [address], [phone], [email]) VALUES (N'Chadwick330', N'KTEEM5RKWZZGE298GCI1L8PL3YFZM0DRPC9X8NAQJDOHMX5QE3NKXXLC3TANPRXVL7R1TC0S1SI8K7Y6', CAST(N'1965-10-10T13:11:38.760' AS DateTime), CAST(N'1997-11-05T12:21:20.130' AS DateTime), 1, 1, N'Tyrone', N'Bradford', N'387 Fabien Drive', N'670-9230223', N'osjaoyf.wtjm@ywolsi.net')
INSERT [dbo].[Customer] ([userName], [password], [created], [lastLogin], [status], [gender], [firstName], [lastName], [address], [phone], [email]) VALUES (N'Dexter', N'PLJDTURRXTEDM0RFSWE', CAST(N'1954-07-03T19:31:11.560' AS DateTime), CAST(N'1992-06-05T08:10:36.550' AS DateTime), 1, 1, N'Esther', N'White', N'592 West White Cowley Street', N'973-239-7838', N'xkfpu@isgywb.org')
INSERT [dbo].[Customer] ([userName], [password], [created], [lastLogin], [status], [gender], [firstName], [lastName], [address], [phone], [email]) VALUES (N'Toni49', N'3X12UI5FCSWU4U06EISDZAWJMFERRTRBK0PRJGIAPBF5XJ9S0K0A316T1IGE4DOJ0KA349MY0IONVQM36LQ6', CAST(N'1980-10-20T18:57:52.790' AS DateTime), CAST(N'2005-02-13T03:45:32.010' AS DateTime), 1, 0, N'Matthew', NULL, N'804 West Second Drive', N'135-803-4670', N'ackrnmhm24@ywalpi.com')
INSERT [dbo].[Customer] ([userName], [password], [created], [lastLogin], [status], [gender], [firstName], [lastName], [address], [phone], [email]) VALUES (N'Jimmy957', N'G8JUWQ0Z35BNV4C85NRX2', CAST(N'1975-09-15T07:32:48.380' AS DateTime), CAST(N'2012-10-18T15:00:10.070' AS DateTime), 1, 0, N'Jennie', N'Peterson', N'76 Clarendon Way', N'544885-2770', N'kyfxbt.cuiis@zkxydmur.iutjwj.com')
INSERT [dbo].[Customer] ([userName], [password], [created], [lastLogin], [status], [gender], [firstName], [lastName], [address], [phone], [email]) VALUES (N'Sonja7', N'LCIXAWD8INIQKCR88ILYWCC0ZM9U0YZ8P6M6CYF4MVNO44KZHXE8B4D80HYOMI7KN91DPLW9', CAST(N'2019-04-15T23:25:08.320' AS DateTime), CAST(N'2015-03-21T08:31:08.790' AS DateTime), 1, 0, N'Evan', N'Fischer', N'320 Green Second Avenue', N'194-7698409', N'upsm4@nilcev.net')
INSERT [dbo].[Customer] ([userName], [password], [created], [lastLogin], [status], [gender], [firstName], [lastName], [address], [phone], [email]) VALUES (N'Leslie83', N'HB1POVU29D3JRWKTD5U0RH4N30V37AW054UTCJBL4LC540WCGJCF19CZ7DMN0QVZG2EM2RGUU8W54YJ24INNYHYVSUL', CAST(N'1957-12-28T04:03:40.430' AS DateTime), CAST(N'1961-07-01T05:35:48.430' AS DateTime), 1, 0, N'Sonny', N'Atkinson', N'51 Rocky Second Drive', N'668-343-2704', N'ccskg.hzogad@hknbnu.com')
INSERT [dbo].[Customer] ([userName], [password], [created], [lastLogin], [status], [gender], [firstName], [lastName], [address], [phone], [email]) VALUES (N'Gwendolyn', N'XOL', CAST(N'1995-11-26T21:25:36.060' AS DateTime), CAST(N'2012-11-27T02:58:35.290' AS DateTime), 0, 0, N'Loretta', N'Beard', N'95 Green Milton Blvd.', N'132415-8478', N'lleyl190@htnnrb.org')
INSERT [dbo].[Customer] ([userName], [password], [created], [lastLogin], [status], [gender], [firstName], [lastName], [address], [phone], [email]) VALUES (N'Linda665', N'QCO1MAAO2HKTD6U8UJJI381Z3IT75IW6NIFSAHGU7YKGW5IM0D4D439IBOX3G3I', CAST(N'1968-02-05T22:50:25.330' AS DateTime), CAST(N'1975-09-16T12:21:15.130' AS DateTime), 1, 1, N'Naomi', N'Lin', N'77 Milton Freeway', N'376-6977544', N'bfnvqq6@uohgya.net')

UPDATE dbo.Customer SET image = (SELECT * FROM OPENROWSET(BULK N'C:/images/customers/default.png', SINGLE_BLOB) as T1)

insert into TypeBlog (code,typeName, createdDate, createdBy)
values('san-xuat-1',N'Sản xuẩt', CAST(N'2022-01-18T09:50:42.460' AS DateTime), 1),
('kien-thuc-2',N'Kiến thức', CAST(N'2022-02-18T09:50:42.460' AS DateTime), 1),
('gia-gas-3',N'Giá gas', CAST(N'2022-03-18T09:50:42.460' AS DateTime), 1)


INSERT [dbo].[Blog] ([code], [adminId], [typeBlogId], [datePost], [lastChange], [status], [title], [content], [img], [description]) VALUES (N'san-xuat-che-cong-nghe-cao-dung-lo-ton-xao-che-bang-gas-1', 1, 1, CAST(N'2023-05-18T09:50:42.460' AS DateTime), CAST(N'2023-05-18T09:50:42.460' AS DateTime), 1, N'Sản xuất chè công nghệ cao dùng lò tôn xao chè bằng gas', N'<p>Từ trước đến nay chè được bà con thu hái và sau đó được xao thủ công các chảo xao chè rất mệt nhọc và vất vả tuy nhiên chè sau khi được xao lại có độ khô không đồng đều, nhiệt độ ngọn lửa ảnh hưởng rất lớn chất lượng chè được xao. Ngoài ra trước nay chè được xao bằng than, củi,..nên trong quá trình cháy sẽ có nhiều khói, muội bám vào chè…gây mất hương vị thơm của chè, không đảm bảo an toàn thực phẩm…</p>', 'images/blogs/1/1.jpg', N'This is a description')
INSERT [dbo].[Blog] ([code], [adminId], [typeBlogId], [datePost], [lastChange], [status], [title], [content], [img], [description]) VALUES (N'quy-trinh-san-xuat-bon-nhua-2', 1, 1, CAST(N'2023-04-12T09:50:42.460' AS DateTime), CAST(N'2023-04-12T09:50:42.460' AS DateTime), 1, N'Quy trình sản xuất bồn nhựa', N'<p>Bồn nhựa là sản phẩm được sản xuất từ nguyên liệu hạt nhựa PE nhập khẩu từ Hàn Quốc, đảm bảo vệ sinh an toàn thực phẩm và độ bền của bồn nước. Sản xuất bằng hạt nhựa nguyên sinh, màu sắc trắng, trong, mịn, thích hợp sử dụng cho mọi nguồn nước. Kể cả những nguồn nước chưa được xử lý tốt còn nhiễm mặn và nhiễm phèn, hóa chất…. có kết cấu tạo từ 3 lớp chống tia UV, hạn chế rong rêu</p>', 'images/blogs/2/1.jpg', N'This is a description')
INSERT [dbo].[Blog] ([code], [adminId], [typeBlogId], [datePost], [lastChange], [status], [title], [content], [img], [description]) VALUES (N'san-xuat-xop-boc-oi-can-dung-gas-loai-nao-3', 1, 1, CAST(N'2023-03-05T09:50:42.460' AS DateTime), CAST(N'2023-03-05T09:50:42.460' AS DateTime), 1, N'Sản xuất xốp bọc ổi cần dùng gas loại nào?', N'<p>Trong quá trình sản xuất xốp bọc ổi Tỉ lệ trộn nhựa PE tinh và nhựa PE phế để sản xuất xốp bọc ổi là 100 hoặc 90/10 đến 85/15. Nếu trộn quá nhiều nhựa phế màng xốp bọc ổi sẽ dễ đứt, màu ngả vàng, chất lượng kém. Gas là nguyên liệu không thể thiếu trong sản xuất xốp bọc ổi. Gas được xử dụng trong sản xuất xốp bọc ổi là gas ở trạng thái gas lỏng ( LPG lổng) được chứa trong các loại bình gas rút lỏng, bình gas 2 van. Khi sản xuất gas lỏng sẽ được rút ra khỏi bình và nén lên áp suất 14 bar, rồi trộn cùng nhựa đã được đun chảy. Thông qua máy thổi xốp gas lỏng và nhựa sẽ được trộn đều trong máy. Khi đượ thổi ra ngoài qua đầu thổi, gas sẽ giãn nở và tạo bọt khí bên trong màng xốp nhựa. Nếu chọn lựa loại gas không đúng sẽ dẫn tới việc màng xốp bị nổ, dễ đứt, xẹp… không kéo được thành màng. Hoặc màng xốp sau khi sản xuất ra có mùi rất khó chịu, lâu bay mùi, ảnh hưởng đến chất lượng sản phẩm.</p>', 'images/blogs/3/1.jpg', N'This is a description')
INSERT [dbo].[Blog] ([code], [adminId], [typeBlogId], [datePost], [lastChange], [status], [title], [content], [img], [description]) VALUES (N'gas-la-thanh-phan-khong-the-thieu-trong-san-xuat-khay-com-hop-4', 1, 1, CAST(N'2023-02-01T09:50:42.460' AS DateTime), CAST(N'2023-02-01T09:50:42.460' AS DateTime), 1, N'Gas là thành phần không thể thiếu trong sản xuất khay cơm hộp', N'<p>Gas phải là loại gas nhập khẩu, thành phần sạch, Không mùi nặng (mùi trứng thối). Gas vốn không có mùi không màu, là khí dễ cháy nổ nên để nhận biết người ta phải pha thêm mùi trứng thối để nhận biết. Tuy nhiên các sản phẩm gas trong nước được pha tỷ lệ mùi này rất cao, nên khi đưa vào thổi xốp sẽ bám mùi rất lâu vào sản phẩm. Vì khay cơm đựng thực phẩm nên khi có mùi thối này sẽ không sử dụng được</p>', 'images/blogs/4/1.jpg', N'This is a description')
INSERT [dbo].[Blog] ([code], [adminId], [typeBlogId], [datePost], [lastChange], [status], [title], [content], [img], [description]) VALUES (N'binh-gas-du-lich-nap-lai-rat-nguy-hiem-5', 1, 2, CAST(N'2023-01-17T09:50:42.460' AS DateTime), CAST(N'2023-01-17T09:50:42.460' AS DateTime), 1, N'Bình gas du lịch nạp lại rất nguy hiểm', N'<p>Khi nhiệt độ môi trường tăng cao, gas lỏng giãn nở nhiệt, đặc biệt nguy hiểm nếu bình bị nạp đầy gas lỏng không có không gian gas hơi, áp suất thủy lực sẽ phá vỡ vỏ bình. Điều này cũng đóng góp vào nguy cơ cháy nổ của bình gas du lịch nạp lại.</p>', 'images/blogs/5/1.jpg', N'This is a description')
INSERT [dbo].[Blog] ([code], [adminId], [typeBlogId], [datePost], [lastChange], [status], [title], [content], [img], [description]) VALUES (N'an-toan-trong-su-dung-gas-binh-6', 1, 2, CAST(N'2023-04-22T09:50:42.460' AS DateTime), CAST(N'2023-04-22T09:50:42.460' AS DateTime), 1, N'An toàn trong sử dụng gas bình', N'<p>Một gas bình LPG chất lượng cao sẽ giúp bạn đảm bảo được an toàn trong quá trình sử dụng. Nên mua gas bình LPG từ các nhà cung cấp đáng tin cậy và được cấp phép để đảm bảo chất lượng. Bạn cũng nên kiểm tra các thông tin như thương hiệu, ngày sản xuất, ngày hết hạn sử dụng, trọng lượng của gas bình LPG để đảm bảo an toàn cho mình và gia đình.</p>', 'images/blogs/6/1.jpg', N'This is a description')
INSERT [dbo].[Blog] ([code], [adminId], [typeBlogId], [datePost], [lastChange], [status], [title], [content], [img], [description]) VALUES (N'thiet-bi-canh-bao-het-gas-7', 1, 2, CAST(N'2023-03-22T09:50:42.460' AS DateTime), CAST(N'2023-03-22T09:50:42.460' AS DateTime), 1, N'Thiết bị cảnh báo hết gas', N'<p>Thiết bị cảnh báo hết gas là một sản phẩm đột phá trong lĩnh vực cung cấp gas công nghiệp. Với thiết kế nhỏ gọn, sản phẩm dễ dàng lắp đặt và sử dụng. Chỉ cần gắn thiết bị lên bình gas, khách hàng sẽ không còn phải lo lắng về việc hết gas trong hệ thống của mình.</p>', 'images/blogs/7/1.jpg', N'This is a description')
INSERT [dbo].[Blog] ([code], [adminId], [typeBlogId], [datePost], [lastChange], [status], [title], [content], [img], [description]) VALUES (N'binh-gas-dat-ngoai-troi-nang-co-chay-no-khong-8', 1, 2, CAST(N'2023-02-12T09:50:42.460' AS DateTime), CAST(N'2023-02-12T09:50:42.460' AS DateTime), 1, N'Bình gas đặt ngoài trời nắng có cháy nổ không?', N'<p>Người dùng thường lo lắng về nguy cơ cháy nổ khi đặt bình gas ngoài trời. Tuy nhiên, các nhà sản xuất gas đã thiết kế van giảm áp và có trách nhiệm giải phóng áp suất để đem lại an toàn cho người sử dụng. Bên cạnh đó, vỏ của bình gas được thiết kế với các màu phản quang như hồng, xanh, vàng để giúp phản xạ nhiệt và ánh sáng. Điều này giúp cho bình gas giảm nhiệt độ khi tiếp xúc với ánh sáng, đặc biệt là trong trường hợp trời nắng.</p>', 'images/blogs/8/1.jpg', N'This is a description')
INSERT [dbo].[Blog] ([code], [adminId], [typeBlogId], [datePost], [lastChange], [status], [title], [content], [img], [description]) VALUES (N'du-bao-ga-thang-7/2023-se-co-nhieu-bien-dong-9', 1, 3, CAST(N'2023-01-16T09:50:42.460' AS DateTime), CAST(N'2023-01-16T09:50:42.460' AS DateTime), 1, N'Dự Báo Gá Tháng 7/2023 Sẽ Có Nhiều Biến Động', N'<p>Công ty TNHH MTV Dầu khí TP.HCM (Saigon Petro) cũng cho biết từ 1/6, giá gas tăng 14.000 đồng bình 12kg, giá bán lẻ tối đa đến tay người tiêu dùng là 375.000 đồng/bình 12kg.</p>', 'images/blogs/9/1.jpg', N'This is a description')
INSERT [dbo].[Blog] ([code], [adminId], [typeBlogId], [datePost], [lastChange], [status], [title], [content], [img], [description]) VALUES (N'gia-gas-moi-nhat-thang-4/2023-tang-1.500-dong/kg-10', 1, 3, CAST(N'2023-04-14T09:50:42.460' AS DateTime), CAST(N'2023-04-14T09:50:42.460' AS DateTime), 1, N'Giá Gas Mới Nhất Tháng 4/2023 Tăng 1.500 Đồng/Kg', N'<p>Theo đó, giá gas tháng 6 sẽ tăng 1.500 đồng/kg, tương đương 18.000 đồng/bình 12kg so với tháng 5/2018. Nguyên nhân giá gas trong tháng 6/2018 tăng so với tháng 5/2018 được các đơn vị kinh doanh cho biết, là do chịu tác động của giá gas thế giới bình quân tháng 6/2018 vừa công bố ở mức 560 USD/tấn, tăng 57,5 USD/tấn.</p>', 'images/blogs/10/1.jpg', N'This is a description')
INSERT [dbo].[Blog] ([code], [adminId], [typeBlogId], [datePost], [lastChange], [status], [title], [content], [img], [description]) VALUES (N'tin-tuc-gia-thang-3/2023-11', 1, 3, CAST(N'2023-03-11T09:50:42.460' AS DateTime), CAST(N'2023-03-11T09:50:42.460' AS DateTime), 1, N'Tin Tức Giá Tháng 3/2023', N'<p>Nguyên nhân giá gas tăng trong tháng 9/2017 so với tháng 8/2017 được các đơn vị kinh doanh cho biết, do tác động của giá gas thế giới bình quân tháng 9/2017 vừa công bố ở mức 490 USD/tấn, tăng 50 USD/tấn so với tháng 8/2017.</p>', 'images/blogs/11/1.jpg', N'This is a description')
INSERT [dbo].[Blog] ([code], [adminId], [typeBlogId], [datePost], [lastChange], [status], [title], [content], [img], [description]) VALUES (N'gia-gas-thang-2-tiep-tuc-tang-28.000-dong/binh-12', 1, 3, CAST(N'2023-02-10T09:50:42.460' AS DateTime), CAST(N'2023-02-10T09:50:42.460' AS DateTime), 1, N'Giá gas tháng 2 tiếp tục tăng 28.000 đồng/bình', N'<p>Lý giải nguyên nhân giá gas tăng, các công ty cho biết trong tháng 10-2017, giá gas thế giới nhập khẩu tăng 87,5 USD/tấn (lên mức 577,5 USD/tấn) kéo theo sự điều chỉnh giá gas bán lẻ trong nước.</p>', 'images/blogs/12/1.jpg', N'This is a description')


insert into Comment (blogId, content, datePost, customerId, status) values(1,N'Ôi bất ngờ ghê, giớ mới biết', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(1,N'Bài viết hay, cảm ơn của hàng', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(2,N'Quy trình phức tạp quá', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(2,N'Hoá ra là như vậy', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(3,N'Không nghĩ vỏ bọc cũng từ gas', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(3,N'Bài viết có nhiều thông tin hữu ích', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(4,N'Thì ra khay cơm mình ăn cũng từ gas', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(4,N'Khay cơmm mà lại liên quan đến gas', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(5,N'Mình toàn dùng cái thằng nạp lại này', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(5,N'Phải mua loại khác thôi', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(6,N'Quy tắc an toàn hay quá', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(7,N'Cho mình đặt thiết bị này với', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(7,N'Mình mua với ạ', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(7,N'Thiết bị hay quá', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(8,N'Cảm ơn', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(9,N'Đắt quá', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(9,N'Giá gas như vậy thì khổ rồi', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(10,N'Tiền mất giá thật', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(10,N'Đắt quá rồi', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(11,N'Tranh thủ thôi', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(11,N'Giá ga vẫn vậy à', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(12,N'Cảm ơn shop', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(12,N'Ca này khó', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)


insert into Comment (blogId, content, datePost, customerId, status) values(1,N'Ôi bất ngờ ghê, giớ mới biết', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(1,N'Bài viết hay, cảm ơn của hàng', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(2,N'Quy trình phức tạp quá', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(2,N'Hoá ra là như vậy', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(3,N'Không nghĩ vỏ bọc cũng từ gas', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(3,N'Bài viết có nhiều thông tin hữu ích', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(4,N'Thì ra khay cơm mình ăn cũng từ gas', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(4,N'Khay cơmm mà lại liên quan đến gas', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(5,N'Mình toàn dùng cái thằng nạp lại này', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(5,N'Phải mua loại khác thôi', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(6,N'Quy tắc an toàn hay quá', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(7,N'Cho mình đặt thiết bị này với', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(7,N'Mình mua với ạ', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(7,N'Thiết bị hay quá', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(8,N'Cảm ơn', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(9,N'Đắt quá', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(9,N'Giá gas như vậy thì khổ rồi', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(10,N'Tiền mất giá thật', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(10,N'Đắt quá rồi', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(11,N'Tranh thủ thôi', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(11,N'Giá ga vẫn vậy à', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(12,N'Cảm ơn shop', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)
insert into Comment (blogId, content, datePost, customerId, status) values(12,N'Ca này khó', CAST(N'2023-05-19T09:50:42.460' AS DateTime), 1, 1)


INSERT [dbo].[Review] ([customerId], [productId], [rate], [content], [dateRate], [status]) VALUES ( 1, 1, 5, N'Đỉnh quá', CAST(N'2013-05-04T10:20:13.180' AS DateTime), 1)
INSERT [dbo].[Review] ([customerId], [productId], [rate], [content], [dateRate], [status]) VALUES ( 2, 1, 5, N'Xịn', CAST(N'2007-06-06T05:13:04.740' AS DateTime), 1)
INSERT [dbo].[Review] ([customerId], [productId], [rate], [content], [dateRate], [status]) VALUES ( 3, 1, 5, N'Cháy là ác', CAST(N'1981-08-20T05:22:55.530' AS DateTime), 1)
INSERT [dbo].[Review] ([customerId], [productId], [rate], [content], [dateRate], [status]) VALUES ( 1, 2, 5, N'Cháy ác', CAST(N'1980-08-11T06:07:38.990' AS DateTime), 0)
INSERT [dbo].[Review] ([customerId], [productId], [rate], [content], [dateRate], [status]) VALUES ( 3, 2, 5, N'Cháy là ác', CAST(N'1974-06-16T01:54:07.990' AS DateTime), 0)
INSERT [dbo].[Review] ([customerId], [productId], [rate], [content], [dateRate], [status]) VALUES ( 2, 2, 5, N'Cháy là ác', CAST(N'1978-10-08T17:00:35.670' AS DateTime), 1)
INSERT [dbo].[Review] ([customerId], [productId], [rate], [content], [dateRate], [status]) VALUES ( 1, 3, 5, N'Cháy là ác', CAST(N'2011-02-01T17:56:18.970' AS DateTime), 1)
INSERT [dbo].[Review] ([customerId], [productId], [rate], [content], [dateRate], [status]) VALUES ( 2, 3, 5, N'Cháy là ác', CAST(N'1996-06-13T19:33:22.090' AS DateTime), 1)
INSERT [dbo].[Review] ([customerId], [productId], [rate], [content], [dateRate], [status]) VALUES ( 4, 4, 5, N'Cháy là ác', CAST(N'2008-05-27T09:59:17.160' AS DateTime), 0)
INSERT [dbo].[Review] ([customerId], [productId], [rate], [content], [dateRate], [status]) VALUES ( 5, 5, 5, N'Cháy là ác', CAST(N'1975-03-05T07:00:30.710' AS DateTime), 0)
INSERT [dbo].[Review] ([customerId], [productId], [rate], [content], [dateRate], [status]) VALUES ( 6, 6, 5, N'Cháy là ác', CAST(N'2014-08-28T09:43:12.970' AS DateTime), 0)
INSERT [dbo].[Review] ([customerId], [productId], [rate], [content], [dateRate], [status]) VALUES ( 7, 7, 5, N'Cháy là ác', CAST(N'2005-12-05T00:54:45.120' AS DateTime), 0)
INSERT [dbo].[Review] ([customerId], [productId], [rate], [content], [dateRate], [status]) VALUES ( 8, 8, 5, N'Cháy là ác', CAST(N'1984-05-22T19:41:16.030' AS DateTime), 1)
INSERT [dbo].[Review] ([customerId], [productId], [rate], [content], [dateRate], [status]) VALUES ( 9, 9, 5, N'Cháy là ác', CAST(N'2017-10-27T15:03:39.210' AS DateTime), 1)
INSERT [dbo].[Review] ([customerId], [productId], [rate], [content], [dateRate], [status]) VALUES ( 1, 10, 5, N'Cháy là ác', CAST(N'2002-05-01T04:15:29.760' AS DateTime), 1)
INSERT [dbo].[Review] ([customerId], [productId], [rate], [content], [dateRate], [status]) VALUES ( 2, 4, 5, N'Cháy là ác', CAST(N'1970-10-21T18:47:51.980' AS DateTime), 0)
INSERT [dbo].[Review] ([customerId], [productId], [rate], [content], [dateRate], [status]) VALUES ( 3, 1, 5, N'Cháy là ác', CAST(N'1975-07-17T04:23:50.120' AS DateTime), 0)
INSERT [dbo].[Review] ([customerId], [productId], [rate], [content], [dateRate], [status]) VALUES ( 4, 2, 5, N'Cháy là ác', CAST(N'1996-05-08T21:56:49.390' AS DateTime), 1)
INSERT [dbo].[Review] ([customerId], [productId], [rate], [content], [dateRate], [status]) VALUES ( 5, 4, 5, N'Cháy là ác', CAST(N'1983-05-19T16:33:02.210' AS DateTime), 1)
INSERT [dbo].[Review] ([customerId], [productId], [rate], [content], [dateRate], [status]) VALUES ( 6, 5, 5, N'Cháy là ác', CAST(N'1968-01-09T10:44:27.960' AS DateTime), 0)
INSERT [dbo].[Review] ([customerId], [productId], [rate], [content], [dateRate], [status]) VALUES ( 7, 6, 5, N'Cháy là ác', CAST(N'1966-01-10T19:21:34.770' AS DateTime), 1)
INSERT [dbo].[Review] ([customerId], [productId], [rate], [content], [dateRate], [status]) VALUES ( 8, 7, 5, N'Cháy là ác', CAST(N'2004-09-22T16:04:34.200' AS DateTime), 1)
INSERT [dbo].[Review] ([customerId], [productId], [rate], [content], [dateRate], [status]) VALUES ( 9, 8, 5, N'Cháy là ác', CAST(N'1991-03-22T23:59:50.120' AS DateTime), 0)
INSERT [dbo].[Review] ([customerId], [productId], [rate], [content], [dateRate], [status]) VALUES ( 10, 4, 5, N'Cháy là ác', CAST(N'1989-04-12T23:43:20.750' AS DateTime), 0)
INSERT [dbo].[Review] ([customerId], [productId], [rate], [content], [dateRate], [status]) VALUES ( 1, 7, 5, N'Cháy là ác', CAST(N'1985-06-09T19:53:52.730' AS DateTime), 0)
INSERT [dbo].[Review] ([customerId], [productId], [rate], [content], [dateRate], [status]) VALUES ( 2, 8, 5, N'Cháy là ác', CAST(N'1964-09-23T00:39:46.320' AS DateTime), 0)
INSERT [dbo].[Review] ([customerId], [productId], [rate], [content], [dateRate], [status]) VALUES ( 3, 9, 5, N'Cháy là ác', CAST(N'1954-08-28T20:41:21.430' AS DateTime), 0)
INSERT [dbo].[Review] ([customerId], [productId], [rate], [content], [dateRate], [status]) VALUES ( 4, 1, 5, N'Cháy là ác', CAST(N'1988-05-23T17:43:18.700' AS DateTime), 1)

INSERT INTO dbo.Feedback(email, content, reply, repDate, sendDate, status, repBy, roleId) VALUES(N'daoson03112002@gmail.com',N'Lag quá ad ơi', NULL, NULL, GETDATE(), 0, NULL, 4)
INSERT INTO dbo.Feedback(name, subject, email, content, reply, repDate, sendDate, status, repBy, roleId) 
VALUES (N'Nguyễn Văn A', N'Lỗi đăng ký tài khoản', N'abc@gmail.com', N'Lỗi không đăng ký được tài khoản', NULL, NULL,GETDATE(),0, NULL, 4)



insert FAQ ([isActive],question,answer,createdBy,createdDate) values (1,
N'Làm thế nào để tiết kiệm gas khi nấu ăn?',
N'Đây là vấn đề rất nhiều người quan tâm. Cách giúp bạn tối thiểu hóa được lượng gas cần dùng nhưng vẫn đảm bảo hiệu quả::Tiết kiệm gas khi nấu ăn:+ Điều chỉnh lửa nhỏ phần thân lửa là nơi có nhiệt độ cao nhất nên bạn có thể sử dụng lửa vừa hoặc nhỏ để đạt hiệu quả khi nấu ăn tốt hơn.:+ Hạn chế bật tắt gas: khi bật tắt bếp nhiều lần thì lượng gas thoát ra ngoài sẽ càng nhiều, từ đó gây lãng phí nên bạn cần chuẩn bị đầy đủ trước khi nấu ăn để giảm tình trạng này.:+ Kết hợp với thiết bị tiết kiệm năng lượng: bạn có thể dùng ấm siêu tốc, nồi áp suất,... để hỗ trợ trong công tác nấu nướng để đạt hiệu quả cao, tiết kiệm gas và thời gian.',
1,CAST(N'1994-05-24T03:00:17.840' AS DateTime))

insert FAQ ([isActive],question,answer,createdBy,createdDate) values (1,
N'Tại sao lửa bị đỏ và khắc phục tình trạng đó thế nào?',
N'Lửa bị đỏ khi sử dụng có thể do nhiều yếu tố gây ra như::+ Lượng gas đã cạn kiệt:+ Có nước ở dụng cụ nấu ăn rơi xuống lửa hoặc do có nước ở chi tiết đồng:+ Lượng không khí không đủ để duy trì lửa cháy:+ Chi tiết trên mâm đồng bị bụi bám bẩn:Để khắc phục nhanh chóng tình huống này bạn có thể làm vệ sinh bộ phận bếp, kiểm tra bình gas để thay mới kịp thời để không ảnh hưởng khi sử dụng.',
1,CAST(N'1994-05-24T03:00:17.840' AS DateTime))

insert FAQ ([isActive],question,answer,createdBy,createdDate) values (1,
N'Bình gas có bị nổ hay không?',
N'Bình gas không thể bị nổ vì được làm từ thép chính hãng nên rất chắc chắn, đảm bảo an toàn. Nguyên nhân gây nổ thường khiến nhiều người lầm tưởng chính là do khí gas gây ra. :Mỗi bình gas sẽ được trang bị khóa an toàn, tuy nhiên, khi lượng gas thoát ra ngoài do sự điều chỉnh của lò xo mở khóa quá nhiều thì có thể bắt cháy nếu nhận được tia lửa điện cực nhỏ, từ đó dẫn đến hỏa hoạn và gây nổ khí. :Thêm vào đó, nếu phát hiện tình trạng bị xì gas nhưng lại không có cách khắc phục hiệu quả thì van bình sẽ bị nổ văng ra xa, khí gas thoát ra ngoài gây nổ khí hoặc bắt cháy, gây nguy hiểm cho bạn. ',
1,CAST(N'1994-05-24T03:00:17.840' AS DateTime))

insert FAQ ([isActive],question,answer,createdBy,createdDate) values (1,
N'Cách nhận biết bình gas còn hay hết?',
N'Để biết khí gas còn hay hết thì bạn có thể nhận diện qua những đặc điểm sau đây::+ Bật gas liên tục nhiều lần thì lửa mới cháy:+ Lửa cháy nhỏ và có màu đỏ:+ Vỏ bình thường xuyên xuất hiện tình trạng ngưng hơi nước hay còn được gọi là đổ mồ hôi"+ Ước tính thời gian sử dụng gas dựa theo kinh nghiệm"+ Cân bình gas và so sánh với khối lượng của vỏ bình:+ Khi nấu nướng thì đít nồi chảo bị đen:+ Thời gian nấu kéo dài, không thể điều chỉnh lửa to:Khi quan sát thấy những dấu hiệu này thì gas của bạn đã sắp hết, bạn nên nhanh chóng gọi cho Gas Anh Tiệp để đổi gas mới, tránh gây ảnh hưởng trong quá trình sử dụng và sản phẩm cũng được cam kết về chất lượng, an toàn cho người dùng.',
1,CAST(N'1994-05-24T03:00:17.840' AS DateTime))

insert FAQ ([isActive],question,answer,createdBy,createdDate) values (1,
N'Các tiêu chí an toàn khi sử dụng gas và hệ thống gas gia đình là gì?',
N'Những tiêu chí an toàn cơ bản cần đáp ứng khi sử dụng gas và hệ thống gas gia đình::+ Vỏ bình gas còn mới, đảm bảo an toàn, không bị gỉ sét nhiều, không bị móp méo, còn hạn kiểm định:+ Khối lượng được đảm bảo, có thể sai sót trong mức chấp nhận là 0.1kg:+ Phụ kiện chuyên dụng còn hạn, đầu nối kín không bị hở gây rò rỉ khí gas:+ Địa chỉ cung cấp gas uy tín, có thương hiệu, nguồn gốc gas rõ ràng',
1,CAST(N'1994-05-24T03:00:17.840' AS DateTime))

insert FAQ ([isActive],question,answer,createdBy,createdDate) values (1,
N'Vì sao bật núm bếp nhưng lửa không cháy?',
N'Tình trạng này xảy ra là do bếp không bắt lửa vì:+ Không khí còn tồn đọng trong ống dẫn nên lượng gas không kịp thời có ngay khi bạn thực hiện thao tác."+ Độ ẩm của không khí cao gây ảnh hưởng đến hoạt động của hệ thống đánh lửa"+ Bật núm bếp chưa đúng cách nên lửa không cháy"+ Khí gas trong bình đã hết',
1,CAST(N'1994-05-24T03:00:17.840' AS DateTime))

insert FAQ ([isActive],question,answer,createdBy,createdDate) values (1,
N'Tại sao bên ngoài bình gas có hiện tượng đổ mồ hôi?',
N'Đây là tình trạng rất thường gặp và khiến cho nhiều người hoang mang. Thật ra nguyên nhân khiến cho vỏ bình gas đổ mồ hôi là do sự chuyển hóa gas pha lỏng thành hơi diễn ra chậm, không đủ đáp ứng nhu cầu sử dụng.:Quá trình chuyển hóa này tượng tự như việc đun nước sôi, do đó khi lượng gas không chuyển hóa kịp để sử dụng thì nhiệt trong bình sẽ được sử dụng để thực hiện điều này khiến cho độ ẩm trong không khí bị ngưng đọng bên ngoài vỏ. Chính vì thế khiến cho tình trạng đổ mồ hôi xảy ra. ',
1,CAST(N'1994-05-24T03:00:17.840' AS DateTime))

insert FAQ ([isActive],question,answer,createdBy,createdDate) values (1,
N'Có nên khóa gas sau khi sử dụng hay không?',
N'Đây là một trong những vấn đề rất quan trọng mà nhiều người bỏ qua. Bạn cần khóa gas ngay sau khi sử dụng để có thể giảm thiểu nhiều vấn đề phát sinh ngoài ý muốn, gây ảnh hưởng đến sự an toàn của bạn và người thân trong gia đình.:Khi khóa gas thì bạn sẽ hạn chế sự rò rỉ khí gas do một số yếu tố như côn trùng cắn đứt ống dẫn, quên tắt bếp khi đang nấu,... ',
1,CAST(N'1994-05-24T03:00:17.840' AS DateTime))


GO
SET IDENTITY_INSERT [dbo].[aboutUs] ON 

INSERT [dbo].[aboutUs] ([aboutUsId], [content], [img]) VALUES (15, N'<h2>Provide Best Product For You</h2>
                                <p>We provide the best Beard oile all over the world. We are the worldd best store in indi for Beard Oil. You can buy our product without any hegitation because they truste us and buy our product without any hagitation because they belive and always happy buy our product.</p>
                                <p>Some of our customer say’s that they trust us and buy our product without any hagitation because they belive us and always happy to buy our product.</p>
                                <p>We provide the beshat they trusted us and buy our product without any hagitation because they belive us and always happy to buy.</p>', N'images/team/1.png')
SET IDENTITY_INSERT [dbo].[aboutUs] OFF
GO
SET IDENTITY_INSERT [dbo].[Member] ON 

INSERT [dbo].[Member] ([memberId], [name], [title], [img], [facebook], [twitter]) VALUES (1, N'Đào Văn Sơn', N'Team Leader', N'images/team/1.png', N'https://www.facebook.com/profile.php?id=100014883102324', N'')
INSERT [dbo].[Member] ([memberId], [name], [title], [img], [facebook], [twitter]) VALUES (2, N'Nguyễn Tuấn Cường', N'Team Member', N'images/team/2.png', N'https://www.facebook.com/profile.php?id=100014883102324', N'')
INSERT [dbo].[Member] ([memberId], [name], [title], [img], [facebook], [twitter]) VALUES (3, N'Nguyễn Xuân Hậu', N'Team Member', N'images/team/3.png', N'https://www.facebook.com/profile.php?id=100014883102324', N'')
INSERT [dbo].[Member] ([memberId], [name], [title], [img], [facebook], [twitter]) VALUES (4, N'Trịnh Hoàng Dũng', N'Team Member', N'images/team/4.png', N'https://www.facebook.com/profile.php?id=100014883102324', N'')
INSERT [dbo].[Member] ([memberId], [name], [title], [img], [facebook], [twitter]) VALUES (5, N'Hoàng Minh Tuấn', N'Team Member', N'images/team/1.png', N'https://www.facebook.com/profile.php?id=100014883102324', N'')
SET IDENTITY_INSERT [dbo].[Member] OFF
GO

USE master

-- DELETE Attribute

-- DELETE Product

-- DBCC CHECKIDENT ('product', RESEED, 0);

