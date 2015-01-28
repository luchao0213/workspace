/*
 * Constant.h
 *
 *  Created on: 2014-9-19
 *      Author: william
 */

#ifndef CONSTANT_H_
#define CONSTANT_H_

#define	RIGHT		0x0000
#define	DOWN		0x0001
#define UP			0x0002
#define	LEFT		0x0003
#define	DONE		0x0100
#define	RIGHTDONW	0x0100
#define	DOWNDONE	0x0101
#define	UPDONE		0x0102
#define	LEFTDONE	0x0103
#define	DIREMASK	0x00FF

enum BODY_TYPE
{
	WALL = 1,
	MAINCHACTER,
	MONSTER,
	FIRE,
};


#endif /* CONSTANT_H_ */
