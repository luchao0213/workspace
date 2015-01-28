/*
 * MainScene.h
 *
 *  Created on: 2014-8-31
 *      Author: william
 */

#ifndef MAINSCENE_H_
#define MAINSCENE_H_

#include "cocos2d.h"

class MainScene : public cocos2d::Layer
{
public:
	//
    static cocos2d::Scene* createScene();

	//Here's a different method in cocos2d-x return bool. instead of 'id' in cocos2d-iphone
	virtual bool init();

	void menuStartCallback(cocos2d::Ref* pSender);
	void menuSetCallback(cocos2d::Ref* pSender);
	void menuAboutCallback(cocos2d::Ref* pSender);
	void menuExitCallback(cocos2d::Ref* pSender);

	//implement the "static *create()" method manually
	CREATE_FUNC(MainScene);
};

#endif /* MAINSCENE_H_ */
