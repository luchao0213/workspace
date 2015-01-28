/*
 * StartScene.h
 *
 *  Created on: 2014-8-31
 *      Author: william
 */

#ifndef STARTSCENE_H_
#define STARTSCENE_H_
#include "cocos2d.h"
#include "MainCharcter.h"
#include "Monster.h"

USING_NS_CC;

class StartScene : public Layer
{
public:
	StartScene();
	virtual ~StartScene();
	CREATE_FUNC(StartScene);
	static Scene* createScene();
	virtual bool init();
	void colseCallback(cocos2d::Ref* pSender);
    void update(float);
	virtual bool onTouchBegan(Touch* touch, Event* event);
	virtual void onTouchMoved(Touch* touch, Event* evnet);
	virtual void onTouchEnded(Touch* touch, Event* evnet);
	virtual void onTouchCancelled(Touch* touch, Event* event);
	virtual bool onContactBegin(const PhysicsContact& contact);
private:
    MainCharcter* male;
	Vec2 dest;
	TMXTiledMap *tileMap;
    bool init_menus();
    bool init_sprites();
    bool init_listener();
};

#endif /* STARTSCENE_H_ */
