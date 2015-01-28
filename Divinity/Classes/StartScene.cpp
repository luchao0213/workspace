/*
 * StartScene.cpp
 *
 *  Created on: 2014-8-31
 *      Author: william
 */

#include "StartScene.h"
#include "MainCharcter.h"
#include "Fire.h"
#include "Constant.h"

USING_NS_CC;
StartScene::StartScene() {
	// TODO Auto-generated constructor stub
}
StartScene::~StartScene() {
	// TODO Auto-generated destructor stub
}
Scene* StartScene::createScene() {
	auto scene = Scene::createWithPhysics();
	auto layer = StartScene::create();
	scene->getPhysicsWorld()->setDebugDrawMask(PhysicsWorld::DEBUGDRAW_ALL);
	scene->getPhysicsWorld()->setGravity(Vect::ZERO);
	Size visibleSize = Director::getInstance()->getVisibleSize();
	scene->addChild(layer);
	return scene;
}
bool StartScene::init() {
	if (!Layer::init()) {
		return false;
	}
	if (!init_menus())
		return false;
	if (!init_sprites())
		return false;
	if (!init_listener())
		return false;
	return true;
}

bool StartScene::init_menus() {
	Size visibleSize = Director::getInstance()->getVisibleSize();
	Vec2 origin = Director::getInstance()->getVisibleOrigin();
	auto closeItem = MenuItemImage::create("CloseNormal.png",
			"CloseSelected.png", CC_CALLBACK_1(StartScene::colseCallback,this));

	closeItem->setPosition(
			Vec2(
					origin.x + visibleSize.width / 2
							- closeItem->getContentSize().width / 2,
					origin.y + visibleSize.height / 2
							- closeItem->getContentSize().height / 2));

	auto menu = Menu::create(closeItem, nullptr);
	this->addChild(menu, 0);
	return true;
}

bool StartScene::init_sprites() {
	Size visibleSize = Director::getInstance()->getVisibleSize();
	Vec2 origin = Director::getInstance()->getVisibleOrigin();
	dest = Vec2(origin.x + visibleSize.width / 2,
			origin.y + visibleSize.height / 2);
	dest = Vec2(((int) dest.x / 64) * 64, ((int) dest.y / 64) * 64);
    tileMap = TMXTiledMap::create("untitled.tmx");
    tileMap->setScale(0.6);
    tileMap->setAnchorPoint(Vec2(0.0,0.0));
    auto cSize = tileMap->getContentSize();
    auto mSize = tileMap->getMapSize();
    tileMap->setPosition(mSize);
    this->addChild(tileMap);
    TMXLayer *layer=tileMap->getLayer("Meta");
    TMXLayer *monster=tileMap->getLayer("Monsters");
    int cntW =(int) layer->getContentSize().width/layer->getMapTileSize().width;
    int cntH =(int) layer->getContentSize().height/layer->getMapTileSize().height;
    for(int w = 0; w < cntW; w++){
    	for(int h = 0; h < cntH; h++)
    	{
    	    Sprite * sp=layer->getTileAt(Vec2(w,h));
    	    if(sp != nullptr)
    	    {
    	    	PhysicsBody *body = PhysicsBody::createBox(Size(60,60));
    	    	body->setDynamic(false);
    	    	body->setContactTestBitmask(0xffffffff);
    	    	body->setCollisionBitmask(0xffffffff);
    	    	int bcat = body->getCategoryBitmask();
    	    	int bcon = body->getContactTestBitmask();
    	    	int bcol = body->getCollisionBitmask();

    	    	CCLog("cat=0x%08x bcon=0x%08x bcol=0x%08x",bcat,bcon,bcol);
    	    	sp->setTag(WALL);
    	    	body->setTag(WALL);
    	    	sp->setPhysicsBody(body);
    	    }
    	}
    }
    auto monsters = tileMap->getObjectGroup("Monsters");
    auto& objs = monsters->getObjects();
    for(auto& obj:objs)
    {
    	ValueMap& dict = obj.asValueMap();
    	float x = dict["x"].asFloat();
    	float y = dict["y"].asFloat();
    	auto monster = Monster::create("animals/zombie.png",4,4);
    	tileMap->addChild(monster,1);
    	Vec2 posWorld = Vec2(x,y);
    	monster->setPosition(posWorld);
    	monster->setDestination(posWorld);
    }

    TMXObjectGroup* maingroup = tileMap->getObjectGroup("MainChacter");
    ValueMap valueMap = maingroup->getObject("maxian");
    float x = valueMap["x"].asFloat();
    float y = valueMap["y"].asFloat();
	male = MainCharcter::create("animals/maincharcter.png", 4, 4);
	male->setPosition(x,y);
	male->setDestination(Vec2(x,y));
	male->logicUpdate();
	male->setTag(MAINCHACTER);
	tileMap->addChild(male, 1);

	auto fire = Fire::create("fire.png", 4, 1);
	fire->setPosition(dest);
	fire->burning();
	tileMap->addChild(fire, 1);

	return true;
}

bool StartScene::init_listener() {
	auto listener1 = EventListenerTouchOneByOne::create();
	listener1->setSwallowTouches(true);
	listener1->onTouchBegan = CC_CALLBACK_2(StartScene::onTouchBegan,this);
	listener1->onTouchMoved = CC_CALLBACK_2(StartScene::onTouchMoved,this);
	listener1->onTouchEnded = CC_CALLBACK_2(StartScene::onTouchEnded,this);
	listener1->onTouchCancelled =
			CC_CALLBACK_2(StartScene::onTouchCancelled,this);
	_eventDispatcher->addEventListenerWithSceneGraphPriority(listener1, this);
	auto contactListener = EventListenerPhysicsContact::create();
	contactListener->onContactBegin =
			CC_CALLBACK_1(StartScene::onContactBegin,this);
	_eventDispatcher->addEventListenerWithSceneGraphPriority(contactListener,
			tileMap);
	return true;
}

void StartScene::update(float fDelta) {
	CCLog("update");
}

bool StartScene::onContactBegin(const PhysicsContact& contact) {
	CCLog("onContactBegin");
	MainCharcter *c;
	Monster *m;
	PhysicsShape * roleA = contact.getShapeA();
	PhysicsShape * roleB = contact.getShapeB();
	int tagA = roleA->getBody()->getTag();
	int tagB = roleB->getBody()->getTag();
	if((tagB == MONSTER && tagA != MONSTER)|| (tagB == MAINCHACTER && tagA != MONSTER))
	{
		PhysicsShape * tmpRole = roleA;
		roleA = roleB;
		roleB = tmpRole;
		int tag = tagA;
		tagA = tagB;
		tagB = tag;
	}
	CCLog("onContact tagA=%d tagB=%d",tagA,tagB);
	switch (tagA) {
	case MONSTER:
		switch (tagB) {
		case MAINCHACTER:
			c = (MainCharcter*) tileMap->getChildByTag(tagB);
			c->die();
			break;
		case WALL:
			m = (Monster*) tileMap->getChildByTag(tagA);
			m->turnBack();
			break;
		case MONSTER:
			m = (Monster*) tileMap->getChildByTag(tagA);
			m->turnBack();
			m = (Monster*)this->getChildByTag(tagB);
			m->turnBack();
			break;
		case FIRE:
			m = (Monster*) tileMap->getChildByTag(tagA);
			m->die();
			break;
		}
		break;
	case MAINCHACTER:
		switch (tagB) {
		case FIRE:
		case MONSTER:
			c = (MainCharcter*)tileMap->getChildByTag(tagA);
			c->die();
			break;
		}
		break;
	}
	return false;
}

bool StartScene::onTouchBegan(Touch* touch, Event* event) {
	dest = tileMap->convertToNodeSpace(touch->getLocation());
	dest.x = dest.x + 32 - (int)(dest.x+32)%64;
	dest.y = dest.y + 32 - (int)(dest.y+32)%64;
	male->setDestination(dest);
	male->logicUpdate();
	return false;
}

void StartScene::onTouchMoved(Touch* touch, Event* event) {
	printf("onTouchMoved\n");
}

void StartScene::onTouchEnded(Touch* touch, Event* evnet) {
	printf("onTouchEnded\n");
}

void StartScene::onTouchCancelled(Touch* touch, Event* evnet) {
	auto point = touch->getLocation();
	printf("onTouchEnded(%f,%f)\n", point.x, point.y);
}

void StartScene::colseCallback(cocos2d::Ref* pSender) {
	Director::getInstance()->popScene();
}

