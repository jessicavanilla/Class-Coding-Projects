# Roxanne Campbell

.intel_syntax noprefix
.data

Selected:
	.ascii "You Selected:\n\0"
Prompt:
	.ascii "How much are you paying today?\n\0"
Change:
	.ascii " dollars is you change, thank you!\n\0"

Hook:
	.ascii "1: 6MM Crochet Hook (3 Dollars)\n\0"
White:
	.ascii "2: 50 Yards White Floofy Yarn (7 Dollars)\n\0"
Green:
	.ascii "3: Yards Green Yarn (5 Dollars)\n\0"
Stuffing:
	.ascii "4: Gallons Stuffing (18 Dollars)\n\0"
Cancel:
	.ascii "5: Cancel Transaction (0 Dollars)\n\0"
Entries:
	.quad Hook
	.quad White
	.quad Green
	.quad Stuffing
	.quad Cancel
Costs:
	.quad 3
	.quad 7
	.quad 5
	.quad 18
	.quad 0

.text
.global _start

_start:
	# Part 1 - Print Options
	mov rdx, [Entries]   
	call PrintZString
	mov rdx, [Entries+8]
	call PrintZString
	mov rdx, [Entries+16]
	call PrintZString
	mov rdx, [Entries+24]
	call PrintZString
	mov rdx, [Entries+32]
	call PrintZString

	#Part 2 - Get Selection Index
	call ScanInt          
	mov rsi, rdx
	sub rsi, 1

	#Part 3 - Print Selection
	lea rdx, Selected     #Part 3
	call PrintZString
	mov rdx, [Entries + rsi * 8]
	call PrintZString

	#Part 4 - Get Payment
	lea rdx, Prompt
	call PrintZString
	call ScanInt
	mov rax, rdx

	#Part 5 - Calculate and Print Change
	mov rbx, [Costs + rsi * 8]
	sub rax, rbx
	mov rdx, rax
	call PrintInt
	lea rdx, Change
	call PrintZString
	

	call Exit
