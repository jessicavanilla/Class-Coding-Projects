# lab2.asm
# Roxanne Campbell

.intel_syntax noprefix
.data
Name:
	.byte 'R'
	.byte 'o'
	.byte 'x'
	.byte 'a'
	.byte 'n'
	.byte 'n'
	.byte 'e'
	.byte ' '
	.byte 'C'
	.byte 'a'
	.byte 'm'
	.byte 'p'
	.byte 'b'
	.byte 'e'
	.byte 'l'
	.byte 'l'
	.byte '\n'
	.byte 0


IncomePrompt:
	.ascii "How much do you earn each month?\n\0"

ExpensePrompt:
	.ascii "How much do you spend on food, rent, etc..?\n\0"

OutputText:
	.ascii "So, your cash flow is $\0"

Income:
	.quad 0

Expense:
	.quad 0

Output:
	.quad 0


.text
.global _start

_start:
	lea rdx, Name
	call PrintZString	    #Print name
	
	lea rdx, IncomePrompt
	call PrintZString	    #prompt for income
	
	call ScanInt
	mov Income, rdx            #store income with Direct Storage

	lea rdx, ExpensePrompt
	call PrintZString	    #prompt for expenses

	call ScanInt
	mov Expense, rdx           #store expense with Direct Storage

	lea rdx, OutputText
	call PrintZString	    #print output text

	mov rax, Income		    #calculate output in rdx and print
	sub rax, Expense
	mov Output, rax
	mov rdx, Output
	call PrintInt

	call Exit

