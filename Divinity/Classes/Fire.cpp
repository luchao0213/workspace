#include "Fire.h"
#include "Constant.h"

USING_NS_CC;
Fire::Fire(){
}
Fire::~Fire(){
}
void Fire::burning()
{
	auto animate =Animate::create(this->getAnimation(0));
	this->runAction(RepeatForever::create(animate));
}
bool Fire::init()
{
	auto texture = TextureCache::sharedTextureCache()->addImage(
			basic.getCString());
	float dWidth = texture->getContentSize().width / cntWidth;
	float dHeight = texture->getContentSize().height / cntHeight;
	PhysicsBody *body = PhysicsBody::createBox(Size(60,60));
	body->setPositionOffset(Vec2(0,64/4-dHeight/4));
	body->setRotationEnable(false);
	body->setGravityEnable(false);
	body->setDynamic(false);
	body->setContactTestBitmask(0xffffffff);
	this->setPhysicsBody(body);
	this->setTag(WALL);
	return true;
}
Fire* Fire::create(const std::string& filename, int cntWidth, int cntHeight) {
	Fire *fire = new Fire();
	if (fire && fire->initWithFileName(filename, cntWidth, cntHeight) && fire->init()) {
		fire->autorelease();
		return fire;
	}
	CC_SAFE_DELETE(fire);
	return nullptr;
}
