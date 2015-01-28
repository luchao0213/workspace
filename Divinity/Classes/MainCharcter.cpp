#include "MainCharcter.h"
#include "Constant.h"

MainCharcter::MainCharcter(){
}
MainCharcter::~MainCharcter()
{
}
MainCharcter* MainCharcter::create(const std::string& filename, int cntWidth, int cntHeight) {
	MainCharcter *maincharcter = new MainCharcter();
	if (maincharcter && maincharcter->initWithFileName(filename, cntWidth, cntHeight) && maincharcter->init()) {
		maincharcter->autorelease();
		return maincharcter;
	}
	CC_SAFE_DELETE(maincharcter);
	return nullptr;
}
void MainCharcter::logicUpdate()
{
	if((status&DONE) != DONE)
	{
		return;
	}
	EventCustom e("main_action");
	e.setUserData(this);
	_eventDispatcher->dispatchEvent(&e);
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

