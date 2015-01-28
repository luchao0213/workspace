#include "Monster.h"
#include "Constant.h"

Monster::Monster(){
}
Monster::~Monster(){
}
bool Monster::init()
{
	mutex = PTHREAD_MUTEX_INITIALIZER;
	status = DONE;
	auto snakeTexture = TextureCache::sharedTextureCache()->addImage(
			basic.getCString());
	float dWidth = snakeTexture->getContentSize().width / cntWidth;
	float dHeight = snakeTexture->getContentSize().height / cntHeight;

	this->setAnchorPoint(Vec2(0.5,32/dHeight));
	PhysicsBody *body = PhysicsBody::createBox(Size(60,60));
	body->setPositionOffset(Vec2(0,64/4-dHeight/4));
	body->setRotationEnable(false);
	body->setGravityEnable(false);
	body->setContactTestBitmask(0xffffffff);
	body->setCollisionBitmask(0xffffffff);
	this->setPhysicsBody(body);
	this->setTag(MONSTER);
	auto listener = EventListenerCustom::create("main_action",
			CC_CALLBACK_1(Monster::receive,this));
	_eventDispatcher->addEventListenerWithFixedPriority(listener, 1);
	return true;

	return false;
}
Monster* Monster::create(const std::string& filename, int cntWidth, int cntHeight) {
	Monster *monster = new Monster();
	if (monster && monster->initWithFileName(filename, cntWidth, cntHeight) && monster->init()) {
		monster->autorelease();
		return monster;
	}
	CC_SAFE_DELETE(monster);
	return nullptr;
}
void Monster::receive(EventCustom* event)
{
	Role *r = (Role*)event->getUserData();
	Vec2 drect= r->getPosition() - this->getPosition();
	if((abs(drect.x) <= 32) || (abs(drect.y) <= 32))
	{
		this->setDestination(r->getPosition());
	}
	this->logicUpdate();
}
void Monster::turnBack()
{
	CCLog("turnBack");
	this->setDestination(Vec2(0,0));
	this->logicUpdate();
}
