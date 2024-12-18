# Jessica Villanueva

.intel_syntax noprefix
.data

Tea:
	.ascii "1. Brisk Iced Tea (25 knuts)\n\0"

Coffee:
	.ascii "2. Iced Coffee (30 knuts)\n\0"

Water:
	.ascii "3. Bottled Water (15 knuts)\n\0"

Cancel:
	.ascii "4. Cancel order (0 knuts)\n\0"

Selection:
	.ascii "Enter your selection:\n\0"

Selected:
	.ascii "You selected:\n\0"

Money:
	.ascii "How many knuts are you feeding it?\n\0"

Change:
	.ascii "Your change in knuts is:\n\0"	

Line:
	.ascii "\n\0"

Names: 
	.quad Tea             # these hold the addresses of where the text is stored
	.quad Coffee
	.quad Water
	.quad Cancel

Costs: 
	.quad 25
	.quad 30
	.quad 15
	.quad 0

.text
.global _start

_start:
	# Print Menu: Part 1
	lea rdx, Tea
	call PrintZString
	lea rdx, Coffee
	call PrintZString
	lea rdx, Water
	call PrintZString
	lea rdx, Cancel
	call PrintZString

	# Get Selection: Part 2
	lea rdx, Selection
	call PrintZString
	call ScanInt
	mov rsi, rdx

	# Print Selection: Part 3
	lea rdx, Selected
	call PrintZString
	mov rdx, [Names + (rsi - 1) * 8]
	call PrintZString

	# Get Money: Part 4
	lea rdx, Money
	call PrintZString
	call ScanInt
	mov rax, rdx 		# rax holds the inputted money

	#Print Change: Part 5
	lea rdx, Change
	call PrintZString
	mov rdx, [Costs + (rsi - 1) * 8] # rdx holds cost of item
	sub rax, rdx
	mov rdx, rax
	call PrintInt

	lea rdx, Line
	call PrintZString

	call Exit
