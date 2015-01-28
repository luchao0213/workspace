#ifndef __MAINCHARCTER__
#define __MAINCHARCTER__
#include "Animal.h"
#include "cocos2d.h"

class MainCharcter : public  Animal
{
public:
	MainCharcter();
	virtual ~MainCharcter();
	static MainCharcter* create(const std::string& filename, int cntWidth, int cntHeight);
	void logicUpdate();
};
#endif /* __MAINCHARCTER__ */
