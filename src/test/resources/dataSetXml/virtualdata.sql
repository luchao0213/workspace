
insert into User(userId, openId, userName, password, userType, locked, lastVisit, lastIp)
		  values(0,'doume','doume', 'e10adc3949ba59abbe56e057f20f883e',1 ,2,'2014-12-17 17:14:59','123.123.123.1');
insert into Administrator(userId) values(0);
insert into Goods(goodsId, goodsName, sellerId, goodsType, locked, description, mediaId, price, creditPrice, retCredit)
		   values(0, 'VIRTUAL_GOODS', 0, 0, 0, 'description','0/1.png', 1,1,1);

insert into User(userId, openId, userName, password, userType, locked, lastVisit, lastIp) 
			values(8000,'FanLi','FanLi', 'e10adc3949ba59abbe56e057f20f883e',2 ,2,'2014-12-17 17:14:59','123.123.123.1');
insert into User(userId, openId, userName, password, userType, locked, lastVisit, lastIp) 
			values(8001,'LvBuWei','LvBuWei', 'e10adc3949ba59abbe56e057f20f883e',2 ,2,'2014-12-17 17:14:59','123.123.123.1');
insert into User(userId, openId, userName, password, userType, locked, lastVisit, lastIp) 
			values(8002,'BaiGui','BaiGui', 'e10adc3949ba59abbe56e057f20f883e',2 ,2,'2014-12-17 17:14:59','123.123.123.1');

insert into Location(userId,distance,lat,lng) values(8000,0,0,0);
insert into Location(userId,distance,lat,lng) values(8001,0,0,0);
insert into Location(userId,distance,lat,lng) values(8002,0,0,0);

insert into BHomePage(userId,mediaId) values(8000,'8000/home.png');
insert into BHomePage(userId,mediaId) values(8001,'8001/home.png');
insert into BHomePage(userId,mediaId) values(8002,'8002/home.png');

insert into Business(userId,bName,bType,locked,ppm,balance,used,capacity,information)
			values(8000,'Fish0',1,0,30,30,50,100,'Nice Shop');
insert into Business(userId,bName,bType,locked,ppm,balance,used,capacity,information)
			values(8001,'Fish1',2,0,30,30,50,100,'Nice Shop');
insert into Business(userId,bName,bType,locked,ppm,balance,used,capacity,information)
			values(8002,'Fish2',4,0,30,30,50,100,'Nice Shop');

insert into Administrator_Business(Administrator_userId,blist_userId) values(0,8000);
insert into Administrator_Business(Administrator_userId,blist_userId) values(0,8001);
insert into Administrator_Business(Administrator_userId,blist_userId) values(0,8002);

insert into Goods(goodsId, goodsName, sellerId, goodsType, locked, description, mediaId, price, creditPrice, retCredit)
		   values(6000, 'Fish', 8000, 1, 0, 'This is fresh fish.','8000/0.png', 10,10,1);
insert into Goods(goodsId, goodsName, sellerId, goodsType, locked, description, mediaId, price, creditPrice, retCredit)
		   values(6001, 'Male', 8001, 2, 0, 'Big and hard.Handsome and Strong.','8001/0.png', 100,100,10);
insert into Goods(goodsId, goodsName, sellerId, goodsType, locked, description, mediaId, price, creditPrice, retCredit)
		   values(6002, 'Anything', 8002, 3, 0, 'Selling you need!','8000/0.png', 100,100,10);

