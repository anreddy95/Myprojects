library(downloader)
fileURL <- "http://archive.ics.uci.edu/ml/machine-learning-databases/breast-cancer-wisconsin/breast-cancer-wisconsin.data"
require(downloader)
download (fileUrl,"breast-cancer-wisconsin.data", mode = "wb")

data <- read.table("breast-cancer-wisconsin.data", na.strings = "?", sep=",")
class(data)
summary(data)
str(data)
data <- data[,-1]
names(data) <- c("ClumpThickness",
                 "UniformityCellSize",
                 "UniformityCellShape",
                 "MarginalAdhesion",
                 "SingleEpithelialCellSize",
                 "BareNuclei",
                 "BlandChromatin",
                 "NormalNucleoli",
                 "Mitoses",
                 "Class")


data$Class <- factor(data$Class, levels=c(2,4), labels=c("benign", "malignant"))
str(data)
data$BareNuclei <- as.factor(data$BareNuclei)
str(data)

set.seed(1234)
ind <- sample(2, nrow(data), replace=TRUE, prob=c(0.7, 0.3))
trainData <- data[ind==1,]
validationData <- data[ind==2,]

library(rpart)
library(rpart.plot)
library(party)

tree = rpart(Class ~ ., data=trainData, method="class")
tree_pred <- predict(tree, validationData, type = "class")

library(caret)
print(tree)
plot(tree)
text(tree)

rpart.plot(tree, extra = 104, nn = TRUE)
rpart.control()
tree_with_params = rpart(Class ~ ., data=trainData, method="class", minsplit = 1, minbucket = 1, cp = -1)
rpart.plot(tree_with_params, extra = 104, nn = TRUE)

library(party)
ctree = ctree(Class ~ ., data=trainData)
print(ctree)
plot(ctree, type="simple")

evaluation <- function(model, data, atype) {
  cat("\nConfusion matrix:\n")
  prediction = predict(model, data, type=atype)
  xtab = table(prediction, data$Class)
  print(xtab)
  cat("\nEvaluation:\n\n")
  accuracy = sum(prediction == data$Class)/length(data$Class)
  precision = xtab[1,1]/sum(xtab[,1])
  recall = xtab[1,1]/sum(xtab[1,])
  f = 2 * (precision * recall) / (precision + recall)
  
  cat(paste("Accuracy:\t", format(accuracy, digits=2), "\n",sep=" "))
  cat(paste("Precision:\t", format(precision, digits=2), "\n",sep=" "))
  cat(paste("Recall:\t\t", format(recall, digits=2), "\n",sep=" "))
  cat(paste("F-measure:\t", format(f, digits=2), "\n",sep=" "))
}
evaluation(tree, validationData, "class")
evaluation(tree_with_params, validationData, "class")

