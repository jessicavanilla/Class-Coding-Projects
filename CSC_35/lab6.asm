#Roxanne Campbell
#Lab 06
#Section 02

.intel_syntax noprefix
.data

Text1:
	.ascii "There is a random number between 1 and 100, what is it?\n\0"
Text2:
	.ascii "Too low, try again\n\0"
Text3:
	.ascii "Too high, try again\n\0"
Text4:
	.ascii "Congrats, you guessed the number!\n\0"
Text5:
	.ascii "Guess: \0"

.text
.global _start

_start:
	mov rdx, 100
	call Random
	mov rax, rdx
	add rax, 1

	lea rdx, Text1
	call PrintZString

Loop:
	lea rdx, Text5
	call PrintZString
	call ScanInt
	cmp rax, rdx
	je Equal
	cmp rdx, rax
	jl Small
	jmp Big	

Small:
	lea rdx, Text2
	call PrintZString
	jmp Loop

Big:
	lea rdx, Text3
	call PrintZString
	jmp Loop

Equal:
	lea rdx, Text4
	call PrintZString
	call Exit
