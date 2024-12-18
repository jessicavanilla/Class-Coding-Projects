.intel_syntax noprefix
.data
Intro:
	.ascii "Greetings! Before you may proceed, I need a number between 1 and 100\n\0"

Guess:
	.ascii "Enter a number: \0"

Large:
	.ascii "That is too large\n\0"

Small:
	.ascii "That is too small\n\0"

Correct:
	.ascii "Correct! You may enter!\n\0"

Rand:
	.quad 0

.text
.global _start

_start:
	lea rdx, Intro
	Call PrintZString
	
	jmp Int

Int:
	mov rdx, 99
	Call Random
	add rdx, 1
	mov Rand, rdx
	jmp Loop

Loop:
	lea rdx, Guess
	Call PrintZString
	Call ScanInt
	cmp Rand, rdx
	je End

	cmp Rand, rdx
	jl Less

	cmp Rand, rdx
	jg Great

Less:
	lea rdx, Large
	Call PrintZString
	jmp Loop

Great:
	lea rdx, Small
	Call PrintZString
	jmp Loop

End:
	lea rdx, Correct
	Call PrintZString
	Call Exit
