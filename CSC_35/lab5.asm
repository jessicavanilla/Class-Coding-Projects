#Roxanne Campbell
#Lab 5
#Section 02

.intel_syntax noprefix
.data

Text1:
	.ascii "Welcome to the bird quiz!\nIf you were a bird, which one would you be?\n\n\0"
Text2:
	.ascii "Do you implusively honk? (y/n)\n\0"
Text3:
	.ascii "Are you otherwise endearing? (y/n)\n\0"
Text4:
	.ascii "Are you good at swimming? (y/n)\n\0"

Text5:
	.ascii "You're a Goose!\n\0"
Text6:
	.ascii "You're a Seagull!\n\0"
Text7:
	.ascii "You're a Penguin!\n\0"
Text8:
	.ascii "You're a Flamingo!\n\0"

.text
.global _start

_start:
	lea rdx, Text1
	call PrintZString
	lea rdx, Text2
	call PrintZString

	call ScanChar
	cmp dl, 121
	jne FirstNo

FirstYes:
	lea rdx, Text3
	call PrintZString
	call ScanChar
	cmp dl, 121
	je Result1
	jmp Result2

FirstNo:
	lea rdx, Text4
	call PrintZString
	call ScanChar
	cmp dl, 121
	je Result3
	jmp Result4

Result1:
	lea rdx, Text5
	call PrintZString
	jmp Ending

Result2:
	lea rdx, Text6
	call PrintZString
	jmp Ending

Result3:
	lea rdx, Text7
	call PrintZString
	jmp Ending

Result4:
	lea rdx, Text8
	call PrintZString
	jmp Ending

Ending:
	call Exit

