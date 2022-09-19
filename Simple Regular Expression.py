def _parseExpr(expr):
  remExpr = expr
  reqExpr = []
  while(len(remExpr) > 0):
    targetLetter = remExpr[0]
    if(targetLetter is '('):
      remExpr, innerExpr = _parseExpr(remExpr[1:])
      reqExpr.append(innerExpr)
      if(len(remExpr) == 0 or remExpr[0] is not ')'):
        raise ValueError("Invalid string. Expecting ')' ")
      remExpr = remExpr[1:]
    elif(targetLetter is ')'):
      break
    else:
      remExpr = remExpr[1:]
      reqExpr.append(targetLetter)
  return (remExpr, reqExpr)

def parseExpr(expr):
  remExpr, reqExpr = _parseExpr(expr)
  if(len(remExpr) != 0):
    raise ValueError("Cannot parse. Invalid string")
  return reqExpr


def doReverse(expr):
  reqExpr = []
  index = len(expr) - 1
  while(index >= 0):
    elem = expr[index]
    if(type(elem) is list):
      elem = doReverse(elem)
    reqExpr.append(elem)
    index -= 1
  return reqExpr

def doSymplify(expr):
  reqExpr = []
  for index, elem in enumerate(expr):
    if(type(elem) is list):
      elem = doSymplify(elem)
    if(index == 0):
      reqExpr.extend(elem)
    else:
      reqExpr.append(elem)
  return reqExpr

def doOperation(InputList, OptnString):
  ReqList = InputList
  for Optn in OptnString:
    if(Optn is 'R'):
      ReqList = doReverse(ReqList)
    elif(Optn is 'S'):
      ReqList = doSymplify(ReqList)
  return ReqList

def toExprString(InputList):
  OutputString = ""
  for elem in InputList:
    if(type(elem) is list):
      OutputString += "(" + toExprString(elem) + ")"
    else:
      OutputString += elem
  return OutputString

def parseAndGenerateExprTree(ExprTreeString, OptnString):
  ExprTreeList = parseExpr(ExprTreeString)
  OperatedList = doOperation(ExprTreeList, OptnString)
  return toExprString(OperatedList)

Input = "(AB)C((DE)F)/RS"

def main():
  strippedInput = Input.replace(" ", "")
  splittedInput = strippedInput.split("/")
  if(len(splittedInput) != 2):
    print "Invalid input"
    return splittedInput[0]
  ExprTreeString, OptnString = splittedInput
  print (parseAndGenerateExprTree(ExprTreeString, OptnString))

if __name__ == '__main__':
  main()