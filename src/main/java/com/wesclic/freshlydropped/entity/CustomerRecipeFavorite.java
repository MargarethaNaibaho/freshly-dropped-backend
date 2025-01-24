package com.wesclic.freshlydropped.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_favorite")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CustomerRecipeFavorite {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_credential_id")
    @JsonIgnore
    private UserCredential userCredential;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    @JsonIgnore
    private Recipe recipe;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public void softDelete(){
        this.deletedAt = LocalDateTime.now();
    }
}
