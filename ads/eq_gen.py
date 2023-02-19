# This program generates long random equations for testing ADS exercise 2.1

from random import *

# Some configurations, modify however you need it

# The scale of factors, factors are randomly
# picked between -factor_scale and +factor_scale
factor_scale = 100

# Max random exponent
max_exponent = 3

# These are the variables it randomly picks
varnames = [
    "foo",
    "bar",
    "baz",
    "loremipsumdolorsitamet",
    "qwertyuiopasdfghjklzxcvbnm",
    "a", "b", "c", "d", "e", "f",
    "x", "y", "z"
]

# Amount of terms on left side
left_len = 0

# Amount of terms on right side
right_len = 0

def gen_expr(l):
    expr = ""

    for i in range(l):
        n = randint(-factor_scale, factor_scale)
        v = varnames[randint(0, len(varnames) - 1)]
        e = randint(0, max_exponent)
        
        if i != 0 and n >= 0:
            expr += "+"
        expr += str(n)
        expr += v
        expr += "^" + str(e)

    return expr


# Change
expr = gen_expr(left_len) + " = " + gen_expr(right_len)

print(expr)
