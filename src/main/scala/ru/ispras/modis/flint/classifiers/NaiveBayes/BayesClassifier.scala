package flint.src.main.scala.ru.ispras.modis.flint.classifiers.NaiveBayes

import ru.ispras.modis.flint.classifiers.{ClassifierTrainer, ClassificationResult, Classifier, DensityEstimation}
import ru.ispras.modis.flint.instances.{LabelledInstance, Instance}
/**
 * Created with IntelliJ IDEA.
 * User: lena
 * Date: 18.11.13
 * Time: 13:44
 * To change this template use File | Settings | File Templates.
 */


class BayesClassifier[LabelType](labelProb: Map[LabelType,Double], sample: DensityEstimation[LabelType]) extends Classifier[LabelType] {


  override def apply(instance: Instance): ClassificationResult[LabelType] = {

    val result = labelProb.map{case (label, aprioryProb) => (label, aprioryProb + sample.apply(label, instance))}.maxBy(_._2);

    new ClassificationResult[LabelType](result._1,Some(result._2))

  }
}
