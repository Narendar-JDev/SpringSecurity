package in.nkn.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nkn.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	public Customer findByEmail(String email);
	boolean existsByEmail(String email);
	
}
