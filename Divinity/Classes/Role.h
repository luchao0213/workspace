/*
 * Role.h
 *
 *  Created on: 2014-8-31
 *      Author: william
 */

#ifndef ROLE_H_
#define ROLE_H_
#include "cocos2d.h"

USING_NS_CC;

class Role : public Sprite
{
public:
	Role();
	virtual ~Role();
	static Role* create(const std::string& filename,int cntWidth,int cntHeight);
	bool initWithFileName(const std::string& filename,int cntWidth,int cntHeight);
	void setTag(int tag);
	virtual void logicUpdate();
	void die();
protected:
	Animation* getAnimation(int drct);
	String basic;
	int cntWidth;
	int cntHeight;
	float delayPerAct;
	void dead();
};

#endif /* ROLE_H_ */
