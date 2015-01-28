#ifndef __ANIMAL__
#define __ANIMAL__
#include "Role.h"
#include "cocos2d.h"

USING_NS_CC;
class Animal : public Role
{
public:
	Animal();
	virtual ~Animal();
	virtual bool init();
	static Animal* create(const std::string& filename, int cntWidth, int cntHeight);
	void up();
	void left();
	void down();
	void right();
	virtual void die();
	void setDestination(Vec2 d);
	void logicUpdate();
	void doneOneAction();
protected:
	int status;
	Vec2 dest;
	pthread_mutex_t mutex;
private:
	void receive(EventCustom* event);
};
#endif /* __ANIMAL__ */
