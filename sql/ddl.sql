--Member TABLE
drop table if exists member CASCADE;
create table member
(
id varchar(255),
password varchar(255),
primary key (id)
);
--create table member
--(
--id varchar(255),
--password varchar(255),
--latitude double,
--longitude double,
--primary key (id)
--);

--Status Table -> 가게 상태 : 1: 한가 / 2: 바쁨 / 3: 종료
create table status(
status_id int,
status_name varchar(200),
primary key (status_id)
)

Insert into status (status_id, status_name)
values
(1, '한가'),
(2, '바쁨'),
(3, '종료'),
(4, '오픈');

--Restaurant Table -> 가게 table :
CREATE TABLE restaurant (
    restaurant_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    restaurant_name VARCHAR(255),
    address VARCHAR(255),
    tel VARCHAR(20),
    latitude DOUBLE,
    longitude DOUBLE,
    open_hour TIME,
    close_hour TIME,
    status_id INT,
    number_of_likes BIGINT DEFAULT 0,
    FOREIGN KEY (status_id) REFERENCES status(status_id)
    FOREIGN KEY (number_of_likes) REFERENCES likes(number_of_likes)
);

--Orders Table
CREATE TABLE orders(
    order_id bigint auto_increment primary key,
    user_id varchar(255),
    restaurant_id bigint,
    created_at TIMESTAMP,
    is_accepted boolean,
    is_finished boolean,
    is_taken boolean,
    price double,
    is_pay boolean,
    pay_info varchar(500),
    foreign key (user_id) references member(id),
    foreign key (restaurant_id) references restaurant(restaurant_id)
    );

--Like Table
CREATE TABLE likes(
    number_of_likes BIGINT DEFAULT 0,
    user_id varchar(255),
    restaurant_id BIGINT PRIMARY KEY,
    FOREIGN KEY (user_id) REFERENCES member(id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(restaurant_id)
    );

--Appointment Table
CREATE TABLE APPOINTMENT(
    appointment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    label varchar(255),
    restaurant_id BIGINT,
    user_id varchar(255),
    date TIMESTAMP,
    is_finished boolean,
    FOREIGN KEY (user_id) REFERENCES member(id)
    );

CREATE TABLE group_member(
    appointment_id BIGINT,
    user_id varchar(255),
    friend_id varchar(255),
    FOREIGN KEY (user_id) REFERENCES member(id),
    FOREIGN KEY (appointment_id) REFERENCES appointment(appointment_id)
    );