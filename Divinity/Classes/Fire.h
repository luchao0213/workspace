#ifndef __FIRE__
#define __FIRE__
#include "cocos2d.h"
#include "Role.h"
USING_NS_CC;

class Fire : public Role
{
public:
	Fire();
	virtual ~Fire();
	virtual bool init();
	void burning();
	static Fire* create(const std::string& filename,int cntWidth,int cntHeight);
};
#endif /* __FIRE__ */
