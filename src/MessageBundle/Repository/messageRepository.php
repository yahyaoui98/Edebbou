<?php

namespace MessageBundle\Repository;
use Doctrine\ORM\EntityRepository;
use MessageBundle\Entity\message;
use Doctrine\ORM\NonUniqueResultException;
use Doctrine\ORM\Query;

/**
 * messageRepository
 *
 * This class was generated by the Doctrine ORM. Add your own custom
 * repository methods below.
 */
class messageRepository extends \Doctrine\ORM\EntityRepository
{

    public function findEntitiesbyString($str)
    {

        return $this->getEntityManager()
            ->createQuery(
                'SELECT p
         FROM MessageBundle:message p
         WHERE p.title LIKE :str'
            )

        ->setParameter('str','%',$str)
            ->getResult();
    }
}
