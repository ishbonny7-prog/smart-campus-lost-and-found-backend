package smart_campus_lostand_found.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import smart_campus_lostand_found.entity.ItemCategory;
import smart_campus_lostand_found.service.ItemCategoryService;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/item-categories")
public class ItemCategoryController {

    private final ItemCategoryService service ;

        

        public ItemCategoryController(ItemCategoryService service){
            this.service=service;
        }



@PostMapping("/save")
public ResponseEntity<String> save(@RequestBody ItemCategory category){
    service.save(category);
    return ResponseEntity.status(HttpStatus.CREATED)
    .body("Item category saved successfully");
}

@GetMapping("/all")
public List<ItemCategory> findAll()
{
    return service.findAll();
}

@GetMapping("/{id}") 
public ResponseEntity<?> findById(@PathVariable UUID id){
    ItemCategory category = service.findById(id);

    if(category==null){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The item category with that id does not exist");
    }
    return ResponseEntity.ok(category);
}

@DeleteMapping("/delete/{id}")
public ResponseEntity<String> delete(@PathVariable UUID id){
    ItemCategory category = service.findById(id);

    if(category==null){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body("The item category with that id does not exist");
    }

    service.deleteById(id);
    return ResponseEntity.ok("Item category deleted successfully");
}


    @PutMapping ("/update/{id}")
    public ResponseEntity<String> update(
        @PathVariable UUID id,
        @RequestBody ItemCategory category){

            ItemCategory existing= service.findById(id);

            if (existing == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("The item category with that id does not exist");
            }

            existing.setName(category.getName());
            service.save(existing);
            return ResponseEntity.ok("Item category updated successfully") ;
        }


        @GetMapping("/unused")
        public List<ItemCategory> findUnusedCategories(){
            return service.findUnusedCategories();
        }
    
}


