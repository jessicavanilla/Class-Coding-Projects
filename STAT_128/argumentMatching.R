# Goal: Understand and explain R's argument matching rules

greet <- function(greeting = "hello", name1 = "you", name2 = "", ...){
  msg <- paste(greeting, name1, name2, ...)
  msg
}

# 1: no arguments
greet()
# 2: 1 positional argument
greet("Hey")
# 3: 3 positional arguments
greet("Hi", "Jess", "Jani")
# 4: named arguments
greet(name2 = "Jessica")
# 5: mixing named arguments
greet(name2 = "hi", greeting = "Jess", name1 = "Jani")
# 6: mixing positional with named arguments
greet("Hi", name1 = "Henry")
# 7
# R is matching the named arguments first, building sequentially as follows:
# greet(name1 = "Henry")
# and then matches the positional arguments second
greet(name1 = "Henry", "Hi")
# 8
greet("Hi", greeting = "Maha")
# 9
# named arguments that don't match any formals in the function go into ...
# formal arguments are {greeting, name1, name2}
greet(foo = "!!!!!")
# 10
greet("!!!!")
# 11 
# After we exhaust all positional arguments, the remaining positional arguments
# go into ...
greet("Hi", "Jess", "Jani", "!!!!!")
# 12
# partial argument matching (does not go into ...)
greet(greet = "hi")
greet(g = "hi")
# 13
# partial argument matching does not work when it's ambiguous
greet(n = "Ben")
# 14
greet(n = "Jani", name1 = "Jess")
# 15
greet(n = "Clark", "Ian", name1 = "Christian")
greet(n = "Clark", , "Ian", name1 = "Christian")

# Best practice:
# Put positional arguments first
# THEN all your named arguments
# f(arg1, arg2, nm1 = namedarg1, nm2 = namedarg2, ...)
greet("hi", name2 = "there")
