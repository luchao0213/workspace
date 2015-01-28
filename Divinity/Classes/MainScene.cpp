/*
 * MainScene.cpp
 *
 *  Created on: 2014-8-31
 *      Author: william
 */

#include "MainScene.h"
#include "HelloWorldScene.h"
#include "StartScene.h"

USING_NS_CC;

Scene* MainScene::createScene()
{
    // 'scene' is an autorelease object
    auto scene = Scene::create();
    Vect g = Vect(0,0);
    // 'layer' is an autorelease object
    auto layer = MainScene::create();

    // add layer as a child to scene
    scene->addChild(layer);

    // return the scene
    return scene;
}
bool MainScene::init()
{
	if(!Layer::init())
	{
		return false;
	}

    Size visibleSize = Director::getInstance()->getVisibleSize();
    Vec2 origin = Director::getInstance()->getVisibleOrigin();

    /////////////////////////////
    // 2. add a menu item with "X" image, which is clicked to quit the program
    //    you may modify it.

    // add a "close" icon to exit the progress. it's an autorelease object
    auto startItem = MenuItemFont::create(
                                           "Start",
                                           CC_CALLBACK_1(MainScene::menuStartCallback, this));

	startItem->setPosition(Vec2(origin.x + visibleSize.width/2,
                                origin.y + visibleSize.height*0.8));

    // add a "close" icon to exit the progress. it's an autorelease object
    auto setItem = MenuItemFont::create(
                                           "Settings",
                                           CC_CALLBACK_1(MainScene::menuSetCallback, this));

    setItem->setPosition(Vec2(origin.x + visibleSize.width/2,
                                origin.y + visibleSize.height*0.6));

    // add a "close" icon to exit the progress. it's an autorelease object
    auto aboutItem = MenuItemFont::create(
                                           "About",
                                           CC_CALLBACK_1(MainScene::menuAboutCallback, this));

    aboutItem->setPosition(Vec2(origin.x + visibleSize.width/2,
                                origin.y + visibleSize.height*0.4));

	// add a "close" icon to exit the progress. it's an autorelease object
    auto exitItem = MenuItemFont::create(
                                           "Exit",
                                           CC_CALLBACK_1(MainScene::menuExitCallback, this));

	exitItem->setPosition(Vec2(origin.x + visibleSize.width/2 ,
                                origin.y + visibleSize.height * 0.2));

    // create menu, it's an autorelease object
    auto menu = Menu::create(startItem,setItem,aboutItem,exitItem, nullptr);
    menu->setPosition(Vec2::ZERO);
    this->addChild(menu, 1);

    /////////////////////////////
    // 3. add your codes below...

    // add a label shows "Hello World"
    // create and initialize a label

    auto label = LabelTTF::create("Chinese God", "Arial", 24);

    // position the label on the center of the screen
    label->setPosition(Vec2(origin.x + visibleSize.width/2,
                            origin.y + visibleSize.height - label->getContentSize().height));

    // add the label as a child to this layer
    this->addChild(label, 1);

    return true;
}

void MainScene::menuStartCallback(cocos2d::Ref* pSender)
{
	auto startScene = StartScene::createScene();
    Director::getInstance()->pushScene(TransitionSlideInR::create(1,startScene));
}
void MainScene::menuSetCallback(cocos2d::Ref* pSender){

}
void MainScene::menuAboutCallback(cocos2d::Ref* pSender)
{
	auto aboutScene = HelloWorld::createScene();
    Director::getInstance()->pushScene(TransitionSlideInR::create(1,aboutScene));
}
void MainScene::menuExitCallback(cocos2d::Ref* pSender){
#if (CC_TARGET_PLATFORM == CC_PLATFORM_WP8) || (CC_TARGET_PLATFORM == CC_PLATFORM_WINRT)
	MessageBox("You pressed the close button. Windows Store Apps do not implement a close button.","Alert");
    return;
#endif

    Director::getInstance()->end();

#if (CC_TARGET_PLATFORM == CC_PLATFORM_IOS)
    exit(0);
#endif
}
