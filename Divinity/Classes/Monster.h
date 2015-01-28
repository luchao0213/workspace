#ifndef __MONSTER__
#define __MONSTER__
#include "cocos2d.h"
#include "Animal.h"

class Monster :public Animal
{
public:
	Monster();
	virtual ~Monster();
	virtual bool init();
	static Monster* create(const std::string& filename, int cntWidth, int cntHeight);
	void receive(EventCustom* event);
	void turnBack();
};
#endif /* __MONSTER__ */
