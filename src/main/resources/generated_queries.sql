


-- testQueryWithExplicitJoin

select
    child.id        as id,
    child.parent_id as parent_id
from Child child
    inner join Parent parent on child.parent_id=parent.id
where parent.name=?

-- testQueryWithoutExplicitJoin

select 
    child.id        as id,
    child.parent_id as parent_id
from Child child 
    cross join Parent parent 
where child.parent_id=parent.id and parent.name=?

-- named query

select 
    child.id        as id
    child.parent_id as parent_id 
from Child child 
    left outer join Parent parent on child.parent_id=parent.id 
where parent.name=?

-- com.luxoft.logeek.ChildRepositoryTest#findByParentIdWithExplicitJoin

select
    child.id        as id,
    child.parent_id as parent_id
from Child child
    inner join Parent parent on child.parent_id=parent.id
where parent.id=?