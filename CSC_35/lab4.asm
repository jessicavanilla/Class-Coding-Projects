#Roxanne Campbell

.intel_syntax noprefix
.data

Text1:
	.ascii "Can you swim? (1=y,0=n)\0\n"
Text2:
	.ascii "Can you fly? (1=y,0=n)\0\n"
Text3:
	.ascii "Do you like parks? (1=y,0=n)\0\n"
Text4:
	.ascii "Do you like obnoxious honking? (1=y,0=n)\0\n"
Text5:
	.ascii "Your total score was: \0"
Text6:
	.ascii "\nYou are likely to be a goose.\0\n"
Text7:
	.ascii "\nYou are unlikely to be a goose.\0\n"

.text
.global _start

_start:
	mov rax, 0
	lea rdx, Text1
	call PrintZString
	call ScanInt
	cmp rdx, 0
	jle QTwo
	add rax, 15

QTwo:
	lea rdx, Text2
	call PrintZString
	call ScanInt
	cmp rdx, 0
	jle QThree
	add rax, 20

QThree:
	lea rdx, Text3
	call PrintZString
	call ScanInt
	cmp rdx, 0
	jle QFour
	add rax, 25

QFour:
	lea rdx, Text4
	call PrintZString
	call ScanInt
	cmp rdx, 0
	jle Check
	add rax, 45

Check:
	lea rdx, Text5
	call PrintZString
	mov rdx, rax
	call PrintInt
	cmp rax, 60
	jl Fail
	lea rdx, Text6
	call PrintZString
	jmp End

Fail:
	lea rdx, Text7
	call PrintZString

End:
	call Exit
	
	
