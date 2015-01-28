/*
 * Role.cpp
 *
 *  Created on: 2014-8-31
 *      Author: william
 */

#include "Role.h"
#include "Constant.h"

USING_NS_CC;

Role::Role() {
	// TODO Auto-generated destructor stub
}
Role::~Role() {
	// TODO Auto-generated destructor stub
}
Role* Role::create(const std::string& filename, int cntWidth, int cntHeight) {
	Role *role = new Role();
	if (role && role->initWithFileName(filename, cntWidth, cntHeight)) {
		role->autorelease();
		return role;
	}
	CC_SAFE_DELETE(role);
	return nullptr;
}

bool Role::initWithFileName(const std::string& filename, int cntWidth,
		int cntHeight) {
	// TODO Auto-generated constructor stub
	this->cntWidth = cntWidth;
	this->cntHeight = cntHeight;
	this->delayPerAct = 0.6;
	this->basic = String(filename);
	auto snakeTexture = TextureCache::sharedTextureCache()->addImage(
			filename);
	float dWidth = snakeTexture->getContentSize().width / cntWidth;
	float dHeight = snakeTexture->getContentSize().height / cntHeight;
	if (!initWithTexture(nullptr, Rect(-dWidth, -dHeight, dWidth, dHeight)))
		return false;
	return true;
}

Animation* Role::getAnimation(int drct) {
	auto animation = Animation::create();
	auto snakeTexture = TextureCache::sharedTextureCache()->addImage(basic.getCString());
	float dWidth = snakeTexture->getContentSize().width / cntWidth;
	float dHeight = snakeTexture->getContentSize().height / cntHeight;
	int x;
	for (x = 0; x < cntWidth; x++) {
		auto snakeRect = Rect(dWidth * x, dHeight * drct, dWidth, dHeight);
		auto spriteFrame = SpriteFrame::createWithTexture(snakeTexture,
				snakeRect);
		animation->addSpriteFrame(spriteFrame);
	}
	animation->setDelayPerUnit(this->delayPerAct / this->cntWidth);
	animation->setLoops(1);
	return animation;
}
void Role::die() {
	auto up = Animate::create(this->getAnimation(UP));
	auto fade = FadeTo::create(0.6, GLubyte(0));
	this->runAction(up);
	auto func = CallFunc::create(CC_CALLBACK_0(Role::dead,this));
	auto action = Sequence::create(fade,func,nullptr);
	this->runAction(action);
}
void Role::dead()
{
	this->removeFromParentAndCleanup(true);
}

void Role::setTag(int tag)
{
	this->_tag = tag;
	this->getPhysicsBody()->setTag(tag);
}

void Role::logicUpdate() {

}
