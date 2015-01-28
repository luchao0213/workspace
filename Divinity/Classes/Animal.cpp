#include "Animal.h"
#include "Constant.h"

USING_NS_CC;
Animal::Animal(){
}
Animal::~Animal(){
}
bool Animal::init()
{
	mutex = PTHREAD_MUTEX_INITIALIZER;
	status = DONE;
	auto texture = TextureCache::sharedTextureCache()->addImage(
			basic.getCString());
	float dWidth = texture->getContentSize().width / cntWidth;
	float dHeight = texture->getContentSize().height / cntHeight;
	this->setAnchorPoint((Vec2(0.5,32 /dHeight)));
	PhysicsBody *body = PhysicsBody::createBox(Size(60,60));
	body->setPositionOffset(Vec2(0,64/4-dHeight/4));
	body->setRotationEnable(false);
	body->setGravityEnable(false);
	body->setContactTestBitmask(0xffffffff);
	body->setCollisionBitmask(0xffffffff);
	this->setPhysicsBody(body);
	return true;
}

Animal* Animal::create(const std::string& filename, int cntWidth, int cntHeight) {
	Animal *animal = new Animal();
	if (animal && animal->initWithFileName(filename, cntWidth, cntHeight) && animal->init()) {
		animal->autorelease();
		return animal;
	}
	CC_SAFE_DELETE(animal);
	return nullptr;
}
void Animal::setDestination(Vec2 d) {
//	pthread_mutex_lock(&mutex);
	dest = d;
//	pthread_mutex_unlock(&mutex);
}

void Animal::up() {
	status = UP;
	auto up = Animate::create(this->getAnimation(UP));
	auto move = MoveBy::create(0.6, Vec2(0, 64));
	auto func = CallFunc::create(CC_CALLBACK_0(Animal::doneOneAction,this));
	auto action = Sequence::create(move,func,nullptr);
	this->runAction(up);
	this->runAction(action);
}
void Animal::left() {
	status = LEFT;
	auto left = Animate::create(this->getAnimation(LEFT));
	auto move = MoveBy::create(0.6, Vec2(-64, 0));
	auto func = CallFunc::create(CC_CALLBACK_0(Animal::doneOneAction,this));
	this->runAction(left);
	auto action = Sequence::create( move,func,nullptr);
	this->runAction(action);
}
void Animal::right() {
	status = RIGHT;
	auto right = Animate::create(this->getAnimation(RIGHT));
	auto move = MoveBy::create(0.6, Vec2(64, 0));
	auto func = CallFunc::create(CC_CALLBACK_0(Animal::doneOneAction,this));
	auto action = Sequence::create(move,func,nullptr);
	this->runAction(right);
	this->runAction(action);
}
void Animal::down() {
	status = DOWN;
	auto down = Animate::create(this->getAnimation(DOWN));
	auto move = MoveBy::create(0.6, Vec2(0, -64));
	auto func = CallFunc::create(CC_CALLBACK_0(Animal::doneOneAction,this));
	this->runAction(down);
	auto action = Sequence::create(move,func,nullptr);
	this->runAction(action);
}
void Animal::die()
{
	_eventDispatcher->removeCustomEventListeners("main_action");
	auto up = Animate::create(this->getAnimation(UP));
	auto fade = FadeTo::create(0.6, GLubyte(0));
	this->runAction(up);
	auto func = CallFunc::create(CC_CALLBACK_0(Animal::dead,this));
	auto action = Sequence::create(fade,func,nullptr);
	this->runAction(action);
}
void Animal::doneOneAction()
{
	status = (status|DONE );
	logicUpdate();
}
void Animal::logicUpdate()
{
	if((status&DONE) != DONE)
	{
		return;
	}
	Vec2 direct = dest - this->getPosition() ;
	if (abs(direct.x) <= 32 && abs(direct.y) <= 32)
	{
		status = DONE;
		return;
	}

	if(abs(direct.x) > abs(direct.y))
	{
		if(direct.x > 0)
		{
			right();
		}else {
			left();
		}
	}else
	{
		if( direct.y > 0)
		{
			up();
		}else
		{
			down();
		}
	}
}
