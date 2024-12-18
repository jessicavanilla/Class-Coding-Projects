.intel_syntax noprefix
.data

Q1:
	.ascii "Are you in college? (1 = yes, 2 = no)\n\0"

Q2:
	.ascii "Are you confident in your choice of major? (1 = yes, 2 = no)\n\0"

Q3:
	.ascii "Are you a commuter? (1 = yes, 2 = no)\n\0"

Q4:
	.ascii "Do you have a good support system? (1 = yes, 2 = no)\n\0"

Q5: 
	.ascii "Do you have imposter syndrome? (1 = yes, 2 = no)\n\0"

Stress:
	.ascii "Your total stress level is at \0"

Fine:
	.ascii "You are going to be okay! Don't worry too much :)\n\0"

Cocoa:
	.ascii "You are so stressed! Have some hot cocoa. You'll be alright.\n\0"

Line:
	.ascii "\n\0"

.text
.global _start

_start:
	lea rdx, Q1
	call PrintZString
	call ScanInt

	cmp rdx, 2
	je PrintQ2
	add rax, 25

PrintQ2:
	lea rdx, Q2
	call PrintZString
	call ScanInt

	cmp rdx, 1
	je PrintQ3
	add rax, 20

PrintQ3:
	lea rdx, Q3
	call PrintZString
	call ScanInt

	cmp rdx, 2
	je PrintQ4
	add rax, 5

PrintQ4:
	lea rdx, Q4
	call PrintZString
	call ScanInt

	cmp rdx, 1
	je PrintQ5
	add rax, 15

PrintQ5:
	lea rdx, Q5
	call PrintZString
	call ScanInt

	cmp rdx, 2
	je End
	add rax, 35

End:
	lea rdx, Stress
	call PrintZString
	mov rdx, rax
	call PrintInt
	lea rdx, Line
	call PrintZString

	cmp rax, 50
	jge PrintCocoa
	

	lea rdx, Fine
	call PrintZString
	jmp E

PrintCocoa:
	lea rdx, Cocoa
	call PrintZString

E:
	call Exit
