<?php

namespace test\Bundle\Repository;

use Doctrine\ORM\NonUniqueResultException;
use Doctrine\ORM\NoResultException;

/**
 * CategoryRepository
 *
 * This class was generated by the Doctrine ORM. Add your own custom
 * repository methods below.
 */
class CategoryRepository extends \Doctrine\ORM\EntityRepository
{
    public function findbyMe5($id)
    {
        $Query=$this->getEntityManager()
            ->createQuery("SELECT l FROM testBundle:Category l 
         WHERE l.id=:id")->setParameter('id',$id);

        return $Query->getResult();

    }
    public function findnbC()
    {
        $Query=$this->getEntityManager()
            ->createQuery("SELECT count(l.id) FROM testBundle:Category l 
         ");

        try {
            return $Query->getSingleScalarResult();
        } catch (NoResultException $e) {
        } catch (NonUniqueResultException $e) {
        }

    }
    public function findidsC()
    {
        $Query=$this->getEntityManager()
            ->createQuery("SELECT l.id FROM testBundle:Category l 
         ");
        return $Query->getResult();

    }


}
