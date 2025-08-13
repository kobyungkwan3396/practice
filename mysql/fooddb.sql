-- 데이터베이스 생성 및 선택
CREATE DATABASE IF NOT EXISTS fooddb;
USE fooddb;

-- 고객 테이블
CREATE TABLE Customer (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(20) UNIQUE,
    email VARCHAR(100),
    address VARCHAR(255),
    birth_date DATE,
    gender ENUM('M', 'F', 'Other'),
    join_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    preferred_menu VARCHAR(100)
);

-- 메뉴 테이블
CREATE TABLE Menu (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    category VARCHAR(50),
    is_available BOOLEAN DEFAULT TRUE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 주문 테이블 (OrderItem 없이 메뉴 정보 포함)
CREATE TABLE `Order` (
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    menu_id INT NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    status ENUM('접수', '조리중', '완료', '취소') DEFAULT '접수',
    total_amount DECIMAL(10,2),
    payment_method ENUM('현금', '카드', '계좌이체', '모바일결제'),
    is_delivery BOOLEAN DEFAULT FALSE,
    staff_id INT,
    FOREIGN KEY (customer_id) REFERENCES Customer(id),
    FOREIGN KEY (menu_id) REFERENCES Menu(id)
);

-- 메뉴 데이터 삽입
INSERT INTO Menu (name, price, category) VALUES
('정어리빙수', 8900, '해산물'),
('코다리마늘빵', 7200, '퓨전'),
('민트미역국', 6500, '국물'),
('생마늘샐러드', 5800, '샐러드'),
('날치알스크류바', 9300, '디저트'),
('직화구이젤라또', 9900, '디저트'),
('죽방멸치튀김', 8700, '튀김'),
('봉골레파스타', 10500, '파스타'),
('마라깐쇼한라봉', 9800, '중식');

-- 고객 데이터 삽입
INSERT INTO Customer (name, phone, email, address, birth_date, gender, preferred_menu) VALUES
('김병관', '010-1234-5678', 'bk.kim@example.com', '경기도 하남시', '1995-03-12', 'M', '직화구이젤라또'),
('이지은', '010-9876-5432', 'jieun.lee@example.com', '서울시 강남구', '1993-07-22', 'F', '봉골레파스타'),
('박판수', '010-9326-6587', 'pansoo@example.com', '서울시 논현동', '1999-09-22', 'M', '죽방멸치튀김'),
('오인제', '010-9126-1689', 'injae@example.com', '서울시 송파구', '1993-08-24', 'M', '봉골레파스타'),
('김민서', '010-9576-4578', 'minseo@example.com', '서울시 강남구', '1993-07-27', 'F', '직화구이젤라또');


-- 주문 예시 삽입
INSERT INTO `Order` (customer_id, menu_id, quantity, total_amount, payment_method, is_delivery, staff_id)
VALUES
(1, 6, 2, 19800, '카드', TRUE, 101),
(2, 8, 1, 10500, '현금', FALSE, 102);


 
